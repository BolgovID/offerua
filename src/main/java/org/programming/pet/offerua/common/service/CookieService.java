package org.programming.pet.offerua.common.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.config.properties.AccessTokenProperties;
import org.programming.pet.offerua.common.config.properties.RefreshTokenProperties;
import org.programming.pet.offerua.common.dto.CookieConstants;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(value = {AccessTokenProperties.class, RefreshTokenProperties.class})
public class CookieService {
    private final AccessTokenProperties accessTokenProperties;
    private final RefreshTokenProperties refreshTokenProperties;

    public HttpCookie createAccessCookie(String accessToken) {
        return ResponseCookie.from(CookieConstants.ACCESS_TOKEN, accessToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(accessTokenProperties.expiresIn())
                .build();
    }

    public HttpCookie createRefreshCookie(String refreshToken) {
        return ResponseCookie.from(CookieConstants.REFRESH_TOKEN, refreshToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(refreshTokenProperties.expiresIn())
                .build();
    }

    public Cookie createExpiredAccessTokenCookie() {
        var accessCookie = new Cookie(CookieConstants.ACCESS_TOKEN, null);
        accessCookie.setPath("/");
        accessCookie.setHttpOnly(true);
        accessCookie.setSecure(false);
        accessCookie.setMaxAge(0);
        return accessCookie;
    }

    public Cookie createExpiredRefreshTokenCookie() {
        var accessCookie = new Cookie(CookieConstants.REFRESH_TOKEN, null);
        accessCookie.setPath("/");
        accessCookie.setHttpOnly(true);
        accessCookie.setSecure(false);
        accessCookie.setMaxAge(0);
        return accessCookie;
    }

    public Optional<String> getAuthToken(HttpServletRequest request) {
        return Optional.ofNullable(WebUtils.getCookie(request, CookieConstants.ACCESS_TOKEN))
                .map(Cookie::getValue);
    }
}
