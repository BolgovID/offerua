package org.programming.pet.offerua.users.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.util.AuthHeaderUtils;
import org.programming.pet.offerua.security.dto.JwtResponseDto;
import org.programming.pet.offerua.security.dto.RefreshToken;
import org.programming.pet.offerua.security.exception.RefreshTokenNotExistException;
import org.programming.pet.offerua.security.model.UserInfo;
import org.programming.pet.offerua.security.repositories.TokenBlacklist;
import org.programming.pet.offerua.security.service.JwtService;
import org.programming.pet.offerua.security.service.RefreshTokenService;
import org.programming.pet.offerua.security.service.factory.AuthenticationTokenFactory;
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
    private final RefreshTokenService refreshTokenService;
    private final TokenBlacklist tokenBlacklist;

    public JwtResponseDto authenticate(String username, String password) {
        var authToken = authenticationTokenFactory.create(username, password);
        var authentication = authenticationManager.authenticate(authToken);

        if (!authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("Invalid user request!");
        }

        var refreshToken = refreshTokenService.createRefreshToken(username);
        var jwtToken = jwtService.generateToken(username);
        log.info("User {} authenticated successfully", username);

        return JwtResponseDto.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken.token())
                .build();
    }

    public JwtResponseDto refreshToken(String refreshToken) {
        var username = refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::user)
                .map(UserInfo::username)
                .orElseThrow(RefreshTokenNotExistException::new);

        refreshTokenService.deleteToken(refreshToken);

        var accessToken = jwtService.generateToken(username);
        var newRefreshToken = refreshTokenService.createRefreshToken(username);

        return JwtResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken.token())
                .build();
    }

    public void logout(HttpServletRequest request) {
        AuthHeaderUtils.extractTokenFromRequest(request)
                .ifPresent(tokenBlacklist::addToBlacklist);
    }
}
