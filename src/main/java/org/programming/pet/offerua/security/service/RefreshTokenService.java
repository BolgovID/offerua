package org.programming.pet.offerua.security.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.util.TimeUtils;
import org.programming.pet.offerua.security.config.properties.JwtProperties;
import org.programming.pet.offerua.security.config.properties.RefreshTokenProperties;
import org.programming.pet.offerua.security.dto.RefreshToken;
import org.programming.pet.offerua.security.exception.RefreshTokenExpiredException;
import org.programming.pet.offerua.security.repositories.RefreshTokenRepository;
import org.programming.pet.offerua.security.service.factory.RefreshTokenFactory;
import org.programming.pet.offerua.users.repository.UserRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(RefreshTokenProperties.class)
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final RefreshTokenFactory refreshTokenFactory;
    private final JwtProperties refreshTokenProperties;

    public RefreshToken createRefreshToken(String username) {
        var user = userRepository.findByUsername(username);
        var refreshToken = refreshTokenFactory.create(user, refreshTokenProperties.expiresIn());
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.expiryDate().isBefore(TimeUtils.currentTime())) {
            refreshTokenRepository.deleteToken(token.token());
            throw new RefreshTokenExpiredException(token.token());
        }
        return token;
    }


    public void deleteToken(String refreshToken) {
        refreshTokenRepository.deleteToken(refreshToken);
    }
}