package org.programming.pet.offerua.users.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.util.JwtUtils;
import org.programming.pet.offerua.common.util.TimeUtils;
import org.programming.pet.offerua.common.config.properties.ResetTokenProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(ResetTokenProperties.class)
public class ResetTokenService {
    private final ResetTokenProperties resetTokenProperties;

    public String generateToken(String email) {
        return JwtUtils.generateToken(email, Collections.emptyMap(), resetTokenProperties.issuer(), resetTokenProperties.expiresIn(), resetTokenProperties.secret());
    }

    public boolean isTokenExpired(String token) {
        return JwtUtils.extractExpiration(token, resetTokenProperties.secret())
                .before(TimeUtils.currentDate());
    }

    public String extractEmail(String token) {
        return JwtUtils.extractSubject(token, resetTokenProperties.secret());
    }
}
