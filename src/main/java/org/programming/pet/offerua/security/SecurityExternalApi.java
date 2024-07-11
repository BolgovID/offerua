package org.programming.pet.offerua.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface SecurityExternalApi {
    JwtResponseDto login(String username, String password, HttpServletResponse servletResponse);

    JwtResponseDto refreshToken(String token, HttpServletResponse servletResponse);

    void logout(HttpServletRequest servletRequest, LogoutRequest logoutRequest);
}
