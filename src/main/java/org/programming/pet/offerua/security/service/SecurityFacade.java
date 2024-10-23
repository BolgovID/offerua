package org.programming.pet.offerua.security.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.security.JwtResponseDto;
import org.programming.pet.offerua.security.SecurityExternalApi;
import org.programming.pet.offerua.common.service.CookieService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SecurityFacade implements SecurityExternalApi {
    private final CookieService cookieService;
    private final AuthService authService;

    @Override
    public JwtResponseDto login(String username, String password, HttpServletResponse servletResponse) {
        var jwtDto = authService.authenticate(username, password);

        var accessCookie = cookieService.createAccessCookie(jwtDto.accessToken());
        var refreshCookie = cookieService.createRefreshCookie(jwtDto.refreshToken());

        servletResponse.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        servletResponse.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        return jwtDto;
    }

    @Override
    public JwtResponseDto refreshToken(String refreshToken, HttpServletResponse servletResponse) {
        log.info("Refresh token to refresh {}", refreshToken);
        var jwtDto = authService.refreshToken(refreshToken);

        var accessCookie = cookieService.createAccessCookie(jwtDto.accessToken());
        var refreshCookie = cookieService.createRefreshCookie(jwtDto.refreshToken());

        servletResponse.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        servletResponse.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
        return jwtDto;
    }

    @Override
    public void logout(HttpServletResponse servletResponse, String accessToken, String refreshToken) {
        authService.invalidateTokens(accessToken, refreshToken);

        var expiredAccessTokenCookie = cookieService.createExpiredAccessTokenCookie();
        var expiredRefreshTokenCookie = cookieService.createExpiredRefreshTokenCookie();

        servletResponse.addCookie(expiredAccessTokenCookie);
        servletResponse.addCookie(expiredRefreshTokenCookie);
    }
}