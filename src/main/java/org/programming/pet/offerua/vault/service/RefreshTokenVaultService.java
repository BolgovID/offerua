package org.programming.pet.offerua.vault.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.config.properties.RefreshTokenProperties;
import org.programming.pet.offerua.vault.persistence.RefreshTokenRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(RefreshTokenProperties.class)
@Slf4j
public class RefreshTokenVaultService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenProperties refreshTokenProperties;

    public Optional<String> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }


    public void deleteToken(String refreshToken) {
        refreshTokenRepository.deleteToken(refreshToken);
        log.debug("Refresh token {} was deleted.", refreshToken);
    }

    public void push(String refreshToken) {
        refreshTokenRepository.save(refreshToken, refreshTokenProperties.expiresIn());
        log.debug("Refresh token {} was saved.", refreshToken);
    }
}