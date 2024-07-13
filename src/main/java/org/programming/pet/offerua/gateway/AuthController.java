package org.programming.pet.offerua.gateway;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.security.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class AuthController {
    private final SecurityExternalApi securityExternalApi;

    @PostMapping("/login")
    public JwtResponseDto authenticateAndGetToken(@RequestBody AuthRequest authRequest, HttpServletResponse servletResponse) {
        log.info("Received POST /login for {}", authRequest.username());
        return securityExternalApi.login(authRequest.username(), authRequest.password(), servletResponse);
    }

    @PostMapping("/refresh-token")
    public JwtResponseDto refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest, HttpServletResponse servletResponse) {
        log.info("Received POST /refresh-redirectTo with {}", refreshTokenRequest);
        return securityExternalApi.refreshToken(refreshTokenRequest.token(), servletResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest servletRequest, @RequestBody LogoutRequest logoutRequest) {
        log.info("Received POST /logout");
        securityExternalApi.logout(servletRequest, logoutRequest);
        return ResponseEntity.ok("Logged out successfully");
    }
}