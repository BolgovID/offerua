package org.programming.pet.offerua.users.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.util.JwtUtils;
import org.programming.pet.offerua.common.util.TimeUtils;
import org.programming.pet.offerua.common.config.properties.VerificationTokenProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(VerificationTokenProperties.class)
public class VerificationTokenService {
    private final VerificationTokenProperties verificationTokenProperties;

    public String generateToken(String username) {
        return JwtUtils.generateToken(
                username,
                Collections.emptyMap(),
                verificationTokenProperties.issuer(),
                verificationTokenProperties.expiresIn(),
                verificationTokenProperties.secret()
        );
    }

    public boolean isTokenExpired(String token) {
        return JwtUtils.extractExpiration(token, verificationTokenProperties.secret())
                .before(TimeUtils.currentDate());
    }

    public String extractUsername(String token) {
        return JwtUtils.extractSubject(token, verificationTokenProperties.secret());
    }
}
