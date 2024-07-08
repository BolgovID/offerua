package org.programming.pet.offerua.security.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.util.RequestUtils;
import org.programming.pet.offerua.security.dto.JwtResponseDto;
import org.programming.pet.offerua.security.dto.LogoutRequest;
import org.programming.pet.offerua.security.dto.RefreshToken;
import org.programming.pet.offerua.security.exception.RefreshTokenNotExistException;
import org.programming.pet.offerua.security.model.UserInfo;
import org.programming.pet.offerua.security.repositories.TokenBlacklist;
import org.programming.pet.offerua.security.service.factory.AuthenticationTokenFactory;
import org.programming.pet.offerua.security.service.factory.CookieResponseFactory;
import org.springframework.http.HttpHeaders;
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
    private final CookieResponseFactory cookieFactory;

    public JwtResponseDto authenticate(String username, String password, HttpServletResponse servletResponse) {
        var authToken = authenticationTokenFactory.create(username, password);
        var authentication = authenticationManager.authenticate(authToken);

        if (!authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("Invalid user request!");
        }

        var refreshToken = refreshTokenService.createRefreshToken(username);
        var accessToken = jwtService.generateToken(username);
        log.info("User {} authenticated successfully", username);

        var cookie = cookieFactory.createAuthCookie(accessToken);
        servletResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return JwtResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.token())
                .build();
    }

    public JwtResponseDto refreshToken(String refreshToken, HttpServletResponse servletResponse) {
        var username = refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::user)
                .map(UserInfo::username)
                .orElseThrow(RefreshTokenNotExistException::new);

        refreshTokenService.deleteToken(refreshToken);

        var accessToken = jwtService.generateToken(username);
        var newRefreshToken = refreshTokenService.createRefreshToken(username);

        var cookie = cookieFactory.createAuthCookie(accessToken);
        servletResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return JwtResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken.token())
                .build();
    }

    public void logout(HttpServletRequest request, LogoutRequest logoutRequest) {
        RequestUtils.extractTokenFromHeader(request)
                .ifPresent(tokenBlacklist::addToBlacklist);
        refreshTokenService.deleteToken(logoutRequest.refreshToken());
    }
}
