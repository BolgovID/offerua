package org.programming.pet.offerua.security.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.util.RequestUtils;
import org.programming.pet.offerua.security.JwtResponseDto;
import org.programming.pet.offerua.security.LogoutRequest;
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
    private final JwtService jwtService;
    private final VaultInternalApi vaultInternalApi;

    public JwtResponseDto authenticate(String username, String password) {
        var authToken = authenticationTokenFactory.create(username, password);
        var authentication = authenticationManager.authenticate(authToken);

        if (!authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("Invalid userInfo request!");
        }
        var refreshToken = vaultInternalApi.generateRefreshToken(username);
        var accessToken = jwtService.generateToken(username);
        log.info("User {} authenticated successfully", username);

        return JwtResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public JwtResponseDto refreshToken(String refreshToken) {
        var username = vaultInternalApi.popUsernameFromRefreshToken(refreshToken);
        log.info("Creating new access and refresh tokens for {}", username);


        var accessToken = jwtService.generateToken(username);
        var newRefreshToken = vaultInternalApi.generateRefreshToken(username);

        return JwtResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    public void logout(HttpServletRequest request, LogoutRequest logoutRequest) {
        RequestUtils.extractTokenFromHeader(request)
                .ifPresent(vaultInternalApi::addJwtToBlacklist);
        var username = vaultInternalApi.popUsernameFromRefreshToken(logoutRequest.refreshToken());
        log.info("User {} logged out successfully", username);
    }

}
