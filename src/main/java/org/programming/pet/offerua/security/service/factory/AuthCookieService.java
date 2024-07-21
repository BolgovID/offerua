package org.programming.pet.offerua.security.service.factory;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.config.properties.AccessTokenProperties;
import org.programming.pet.offerua.common.config.properties.RefreshTokenProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(value = {AccessTokenProperties.class, RefreshTokenProperties.class})
public class AuthCookieService {
    private final AccessTokenProperties accessTokenProperties;
    private final RefreshTokenProperties refreshTokenProperties;

    private static final String REFRESH_TOKEN = "refresh_token";
    public static final String ACCESS_TOKEN = "access_token";

    public HttpCookie createAccessCookie(String accessToken) {
        return ResponseCookie.from(ACCESS_TOKEN, accessToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(accessTokenProperties.expiresIn())
                .build();
    }

    public HttpCookie createRefreshCookie(String refreshToken) {
        return ResponseCookie.from(REFRESH_TOKEN, refreshToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(refreshTokenProperties.expiresIn())
                .build();
    }

    public Cookie createExpiredAccessTokenCookie() {
        var accessCookie = new Cookie(ACCESS_TOKEN, null);
        accessCookie.setPath("/");
        accessCookie.setHttpOnly(true);
        accessCookie.setSecure(false);
        accessCookie.setMaxAge(0);
        return accessCookie;
    }

    public Cookie createExpiredRefreshTokenCookie() {
        var accessCookie = new Cookie(REFRESH_TOKEN, null);
        accessCookie.setPath("/");
        accessCookie.setHttpOnly(true);
        accessCookie.setSecure(false);
        accessCookie.setMaxAge(0);
        return accessCookie;
    }
}
