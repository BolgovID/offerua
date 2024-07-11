package org.programming.pet.offerua.security.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.security.JwtResponseDto;
import org.programming.pet.offerua.security.LogoutRequest;
import org.programming.pet.offerua.security.SecurityExternalApi;
import org.programming.pet.offerua.security.service.factory.CookieResponseFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SecurityFacade implements SecurityExternalApi {
    private final CookieResponseFactory cookieFactory;
    private final AuthService authService;

    @Override
    public JwtResponseDto login(String username, String password, HttpServletResponse servletResponse) {
        var jwtDto = authService.authenticate(username, password);

        var cookie = cookieFactory.createAuthCookie(jwtDto.accessToken());
        servletResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return jwtDto;
    }

    @Override
    public JwtResponseDto refreshToken(String refreshToken, HttpServletResponse servletResponse) {
        var jwtDto = authService.refreshToken(refreshToken);

        var cookie = cookieFactory.createAuthCookie(jwtDto.accessToken());
        servletResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return jwtDto;
    }

    @Override
    public void logout(HttpServletRequest request, LogoutRequest logoutRequest) {
        authService.logout(request, logoutRequest);
    }
}