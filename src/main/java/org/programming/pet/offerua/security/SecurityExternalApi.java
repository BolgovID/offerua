package org.programming.pet.offerua.security;

import jakarta.servlet.http.HttpServletResponse;

public interface SecurityExternalApi {
    JwtResponseDto login(String username, String password, HttpServletResponse servletResponse);

    JwtResponseDto refreshToken(String refreshToken, HttpServletResponse servletResponse);

    void logout(HttpServletResponse servletResponse, String accessToken, String refreshToken);
}
