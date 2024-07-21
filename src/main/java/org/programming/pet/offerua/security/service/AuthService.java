package org.programming.pet.offerua.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.exception.TokenExpiredException;
import org.programming.pet.offerua.security.JwtResponseDto;
import org.programming.pet.offerua.security.exception.SecurityErrorCodes;
import org.programming.pet.offerua.security.service.factory.AuthenticationTokenFactory;
import org.programming.pet.offerua.vault.VaultInternalApi;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final AuthenticationTokenFactory authenticationTokenFactory;
    private final AccessTokenService accessTokenService;
    private final RefreshTokenService refreshTokenService;
    private final VaultInternalApi vaultInternalApi;

    public JwtResponseDto authenticate(String username, String password) {
        var authToken = authenticationTokenFactory.create(username, password);
        var authentication = authenticationManager.authenticate(authToken);

        if (!authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("Invalid userInfo request!");
        }
        var refreshToken = refreshTokenService.generateToken(username);
        vaultInternalApi.pushRefreshToken(refreshToken);
        var accessToken = accessTokenService.generateToken(username);
        log.info("User {} authenticated successfully", username);

        return JwtResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public JwtResponseDto refreshToken(String tokenToRefresh) {
        var refreshToken = vaultInternalApi.popRefreshToken(tokenToRefresh);
        if (refreshTokenService.isTokenExpired(refreshToken)) {
            throw new TokenExpiredException(SecurityErrorCodes.REFRESH_TOKEN_EXPIRED, refreshToken);
        }
        log.info("Creating new access and refresh tokens");
        var username = refreshTokenService.extractUsername(refreshToken);

        var accessToken = accessTokenService.generateToken(username);
        var newRefreshToken = refreshTokenService.generateToken(username);

        vaultInternalApi.pushRefreshToken(newRefreshToken);

        return JwtResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    public void invalidateTokens(String accessToken, String refreshToken) {
        vaultInternalApi.pushJwtToBlacklist(accessToken);
        var deletedRefreshToken = vaultInternalApi.popRefreshToken(refreshToken);

        log.info("Refresh token {}  was deleted, and access token {} was blacklisted", deletedRefreshToken, accessToken);
    }

}
