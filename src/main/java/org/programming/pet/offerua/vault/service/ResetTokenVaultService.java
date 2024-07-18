package org.programming.pet.offerua.vault.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.config.properties.ResetTokenProperties;
import org.programming.pet.offerua.vault.persistence.ResetTokenRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableConfigurationProperties(ResetTokenProperties.class)
public class ResetTokenVaultService {
    private final ResetTokenRepository resetTokenRepository;
    private final ResetTokenProperties resetTokenProperties;

    public Optional<String> findByToken(String token) {
        return resetTokenRepository.findByToken(token);
    }

    public String push(String token) {
        var savedToken = resetTokenRepository.save(token, resetTokenProperties.expiresIn());
        log.info("Reset token {} was saved. Saving...", token);
        return savedToken;
    }

    public void delete(String token) {
        resetTokenRepository.deleteToken(token);
        log.info("Reset token {} was deleted. Saving...", token);
    }
}