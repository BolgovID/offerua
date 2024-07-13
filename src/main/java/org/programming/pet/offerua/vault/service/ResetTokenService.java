package org.programming.pet.offerua.vault.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.util.TimeUtils;
import org.programming.pet.offerua.vault.config.properties.ResetTokenProperties;
import org.programming.pet.offerua.vault.exception.TokenExpiredException;
import org.programming.pet.offerua.vault.exception.VaultErrorCodes;
import org.programming.pet.offerua.vault.persistence.ResetToken;
import org.programming.pet.offerua.vault.persistence.ResetTokenRepository;
import org.programming.pet.offerua.vault.service.factory.ResetTokenFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableConfigurationProperties(ResetTokenProperties.class)
public class ResetTokenService {
    private final ResetTokenRepository resetTokenRepository;
    private final ResetTokenFactory resetTokenFactory;
    private final ResetTokenProperties resetTokenProperties;

    public Optional<ResetToken> findByToken(String token) {
        return resetTokenRepository.findByToken(token);
    }

    public ResetToken verifyExpiration(ResetToken token) {
        if (token.expiryDate().isBefore(TimeUtils.currentTime())) {
            log.error("Reset redirectTo {} expired at {}. Initialize delete...", token.token(), token.expiryDate());
            resetTokenRepository.deleteToken(token.token());
            throw new TokenExpiredException(VaultErrorCodes.RESET_TOKEN_EXPIRED, token.token(), token.expiryDate().toString());
        }
        return token;
    }

    public ResetToken createToken(String email) {
        var resetToken = resetTokenFactory.create(email, resetTokenProperties.expiresIn());
        log.info("Reset redirectTo {} was created. Saving...", resetToken.token());

        return resetTokenRepository.save(resetToken, resetTokenProperties.expiresIn());
    }
}