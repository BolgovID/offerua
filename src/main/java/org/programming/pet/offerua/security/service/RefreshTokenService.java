package org.programming.pet.offerua.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.config.properties.RefreshTokenProperties;
import org.programming.pet.offerua.common.util.JwtUtils;
import org.programming.pet.offerua.common.util.TimeUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(RefreshTokenProperties.class)
@Slf4j
public class RefreshTokenService {
    private final RefreshTokenProperties refreshTokenProperties;

    public String generateToken(String username) {
        return JwtUtils.generateToken(username, Collections.emptyMap(), refreshTokenProperties.issuer(), refreshTokenProperties.expiresIn(), refreshTokenProperties.secret());
    }

    public boolean isTokenExpired(String token) {
        return JwtUtils.extractExpiration(token, refreshTokenProperties.secret()).before(TimeUtils.currentDate());
    }

    public String extractUsername(String token) {
        return JwtUtils.extractSubject(token, refreshTokenProperties.secret());
    }
}
