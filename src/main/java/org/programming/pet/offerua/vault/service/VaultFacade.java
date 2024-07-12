package org.programming.pet.offerua.vault.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.vault.VaultExternalApi;
import org.programming.pet.offerua.vault.VaultInternalApi;
import org.programming.pet.offerua.vault.exception.TokenNotExistException;
import org.programming.pet.offerua.vault.exception.VaultErrorCodes;
import org.programming.pet.offerua.vault.persistence.RefreshToken;
import org.programming.pet.offerua.vault.persistence.ResetToken;
import org.programming.pet.offerua.vault.persistence.VerificationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VaultFacade implements VaultInternalApi, VaultExternalApi {
    private final VerificationTokenService verificationTokenService;
    private final RefreshTokenService refreshTokenService;
    private final JwtBlackListService jwtBlackListService;
    private final ResetTokenService resetTokenService;

    @Override
    public String generateVerificationToken(String username) {
        return verificationTokenService.createToken(username)
                .token();
    }

    @Override
    public String generateRefreshToken(String username) {
        return refreshTokenService.createToken(username)
                .token();
    }

    @Override
    public String generateResetToken(String email) {
        return resetTokenService.createToken(email)
                .token();
    }

    @Override
    public String popUsernameByVerificationToken(String token) {
        var usernameOpt = verificationTokenService.findByToken(token)
                .map(verificationTokenService::verifyExpiration)
                .map(VerificationToken::username);
        usernameOpt
                .ifPresent(verificationTokenService::deleteToken);
        return usernameOpt
                .orElseThrow(() -> new TokenNotExistException(VaultErrorCodes.VERIFICATION_TOKEN_NOT_EXIST, token));
    }

    @Override
    public String popUsernameFromRefreshToken(String token) {
        var usernameOpt = refreshTokenService.findByToken(token)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::username);
        usernameOpt
                .ifPresent(refreshTokenService::deleteToken);
        return usernameOpt
                .orElseThrow(() -> new TokenNotExistException(VaultErrorCodes.REFRESH_TOKEN_NOT_EXIST, token));
    }

    @Override
    public String popUserEmailByResetToken(String token) {
        var emailOpt = resetTokenService.findByToken(token)
                .map(resetTokenService::verifyExpiration)
                .map(ResetToken::email);

        emailOpt.ifPresent(resetTokenService::deleteToken);

        return emailOpt
                .orElseThrow(() -> new TokenNotExistException(VaultErrorCodes.RESET_TOKEN_NOT_EXIST, token));
    }

    @Override
    public String validateResetToken(String token) {
        return resetTokenService.findByToken(token)
                .map(resetTokenService::verifyExpiration)
                .map(ResetToken::token)
                .orElseThrow(() -> new TokenNotExistException(VaultErrorCodes.RESET_TOKEN_NOT_EXIST, token));
    }

    @Override
    public void deleteRefreshToken(String token) {
        refreshTokenService.deleteToken(token);
    }

    @Override
    public void addJwtToBlacklist(String jwt) {
        jwtBlackListService.addToBlacklist(jwt);
    }

    @Override
    public boolean isJwtNotBlacklisted(String token) {
        return jwtBlackListService.isNotBlacklisted(token);
    }
}
