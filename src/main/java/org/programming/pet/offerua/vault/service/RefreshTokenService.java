package org.programming.pet.offerua.vault.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.util.TimeUtils;
import org.programming.pet.offerua.vault.config.properties.RefreshTokenProperties;
import org.programming.pet.offerua.vault.exception.TokenExpiredException;
import org.programming.pet.offerua.vault.exception.VaultErrorCodes;
import org.programming.pet.offerua.vault.persistence.RefreshToken;
import org.programming.pet.offerua.vault.persistence.RefreshTokenRepository;
import org.programming.pet.offerua.vault.service.factory.RefreshTokenFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(RefreshTokenProperties.class)
@Slf4j
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenFactory refreshTokenFactory;
    private final RefreshTokenProperties refreshTokenProperties;

    public RefreshToken createToken(String username) {
        var refreshToken = refreshTokenFactory.create(username, refreshTokenProperties.expiresIn());
        log.info("Refresh redirectTo {} was created. Saving...", refreshToken.token());
        return refreshTokenRepository.save(refreshToken, refreshTokenProperties.expiresIn());
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.expiryDate().isBefore(TimeUtils.currentTime())) {
            log.error("Refresh redirectTo {} expired at {}. Initialize delete...", token.token(), token.expiryDate());
            refreshTokenRepository.deleteToken(token.token());
            throw new TokenExpiredException(VaultErrorCodes.REFRESH_TOKEN_EXPIRED, token.token(), token.expiryDate().toString());
        }
        return token;
    }

    public void deleteToken(String refreshToken) {
        refreshTokenRepository.deleteToken(refreshToken);
        log.info("Refresh redirectTo {} was deleted", refreshToken);
    }

}