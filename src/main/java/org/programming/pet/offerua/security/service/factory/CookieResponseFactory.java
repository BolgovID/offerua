package org.programming.pet.offerua.security.service.factory;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.config.properties.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(JwtProperties.class)
public class CookieResponseFactory {
    private final JwtProperties jwtProperties;

    public static final String ACCESS_TOKEN = "access_token";

    public ResponseCookie createAuthCookie(String accessToken) {
        return ResponseCookie.from(ACCESS_TOKEN, accessToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(jwtProperties.expiresIn())
                .build();
    }
}
