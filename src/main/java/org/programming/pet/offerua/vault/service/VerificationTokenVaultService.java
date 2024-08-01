package org.programming.pet.offerua.vault.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.config.properties.VerificationTokenProperties;
import org.programming.pet.offerua.vault.persistence.VerificationTokenRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableConfigurationProperties(VerificationTokenProperties.class)
public class VerificationTokenVaultService {
    private final VerificationTokenProperties verificationTokenProperties;
    private final VerificationTokenRepository verificationTokenRepository;

    public String push(String token) {
        var savedToken = verificationTokenRepository.save(token, verificationTokenProperties.expiresIn());
        log.info("Verification token was saved: {}", token);
        return savedToken;
    }

    public Optional<String> findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    public void deleteToken(String token) {
        verificationTokenRepository.deleteToken(token);
        log.info("Verification token was deleted: {}", token);
    }
}