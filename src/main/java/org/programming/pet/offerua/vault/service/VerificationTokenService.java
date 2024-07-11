package org.programming.pet.offerua.vault.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.util.TimeUtils;
import org.programming.pet.offerua.vault.config.properties.VerificationTokenProperties;
import org.programming.pet.offerua.vault.exception.VerificationTokenExpiredException;
import org.programming.pet.offerua.vault.persistence.VerificationToken;
import org.programming.pet.offerua.vault.persistence.VerificationTokenRepository;
import org.programming.pet.offerua.vault.service.factory.VerificationTokenFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(VerificationTokenProperties.class)
public class VerificationTokenService {
    private final VerificationTokenFactory verificationTokenFactory;
    private final VerificationTokenProperties verificationTokenProperties;
    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationToken createToken(String username) {
        var verificationToken = verificationTokenFactory.create(username, verificationTokenProperties.expiresIn());
        return verificationTokenRepository.save(verificationToken, verificationTokenProperties.expiresIn());
    }

    public Optional<VerificationToken> findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    public VerificationToken verifyExpiration(VerificationToken token) {
        if (token.expirationDate().isBefore(TimeUtils.currentTime())) {
            verificationTokenRepository.deleteToken(token.token());
            throw new VerificationTokenExpiredException(token.token());
        }
        return token;
    }

    public void deleteToken(String refreshToken) {
        verificationTokenRepository.deleteToken(refreshToken);
    }
}