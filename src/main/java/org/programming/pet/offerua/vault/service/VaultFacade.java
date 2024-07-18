package org.programming.pet.offerua.vault.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.vault.VaultInternalApi;
import org.programming.pet.offerua.vault.exception.TokenNotExistException;
import org.programming.pet.offerua.vault.exception.VaultErrorCodes;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VaultFacade implements VaultInternalApi {
    private final VerificationTokenVaultService verificationTokenVaultService;
    private final RefreshTokenVaultService refreshTokenVaultService;
    private final JwtBlackListService jwtBlackListService;
    private final ResetTokenVaultService resetTokenVaultService;


    @Override
    public void pushRefreshToken(String refreshToken) {
        refreshTokenVaultService.push(refreshToken);
    }

    @Override
    public String popRefreshToken(String token) {
        var usernameOpt = refreshTokenVaultService.findByToken(token);

        usernameOpt.ifPresent(username -> refreshTokenVaultService.deleteToken(token));
        return usernameOpt
                .orElseThrow(() -> new TokenNotExistException(VaultErrorCodes.REFRESH_TOKEN_NOT_EXIST, token));
    }

    @Override
    public void pushVerificationToken(String refreshToken) {
        verificationTokenVaultService.push(refreshToken);
    }

    @Override
    public String popVerificationToken(String token) {
        var usernameOpt = verificationTokenVaultService.findByToken(token);

        usernameOpt.ifPresent(username -> refreshTokenVaultService.deleteToken(token));
        return usernameOpt
                .orElseThrow(() -> new TokenNotExistException(VaultErrorCodes.VERIFICATION_TOKEN_NOT_EXIST, token));
    }

    @Override
    public void pushResetToken(String resetToken) {
        resetTokenVaultService.push(resetToken);
    }

    @Override
    public String popResetToken(String token) {
        var emailOpt = resetTokenVaultService.findByToken(token);

        emailOpt.ifPresent(username -> refreshTokenVaultService.deleteToken(token));
        return emailOpt
                .orElseThrow(() -> new TokenNotExistException(VaultErrorCodes.RESET_TOKEN_NOT_EXIST, token));
    }

    @Override
    public void pushJwtToBlacklist(String jwt) {
        jwtBlackListService.addToBlacklist(jwt);
    }

    @Override
    public boolean isJwtNotBlacklisted(String token) {
        return jwtBlackListService.isNotBlacklisted(token);
    }
}
