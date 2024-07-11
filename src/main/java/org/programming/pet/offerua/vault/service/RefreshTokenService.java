package org.programming.pet.offerua.vault.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.util.TimeUtils;
import org.programming.pet.offerua.vault.config.properties.RefreshTokenProperties;
import org.programming.pet.offerua.vault.exception.RefreshTokenExpiredException;
import org.programming.pet.offerua.vault.persistence.RefreshToken;
import org.programming.pet.offerua.vault.persistence.RefreshTokenRepository;
import org.programming.pet.offerua.vault.service.factory.RefreshTokenFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(RefreshTokenProperties.class)
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenFactory refreshTokenFactory;
    private final RefreshTokenProperties refreshTokenProperties;

    public RefreshToken createToken(String username) {
        var refreshToken = refreshTokenFactory.create(username, refreshTokenProperties.expiresIn());
        return refreshTokenRepository.save(refreshToken, refreshTokenProperties.expiresIn());
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