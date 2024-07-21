package org.programming.pet.offerua.gateway;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.security.AuthRequest;
import org.programming.pet.offerua.security.JwtResponseDto;
import org.programming.pet.offerua.security.SecurityExternalApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final SecurityExternalApi securityExternalApi;

    @PostMapping("/login")
    public JwtResponseDto authenticateAndGetToken(@RequestBody AuthRequest authRequest, HttpServletResponse servletResponse) {
        log.info("Received POST /login for {}", authRequest.username());
        return securityExternalApi.login(authRequest.username(), authRequest.password(), servletResponse);
    }

    @PostMapping("/refresh-token")
    public JwtResponseDto refreshToken(@CookieValue("refresh_token") String refreshToken, HttpServletResponse servletResponse) {
        log.info("Received POST /refresh-token");
        return securityExternalApi.refreshToken(refreshToken, servletResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @CookieValue("access_token") String accessToken,
            @CookieValue("refresh_token") String refreshToken,
            HttpServletResponse servletResponse
    ) {
        log.info("Received POST /logout");
        securityExternalApi.logout(servletResponse, accessToken, refreshToken);
        return ResponseEntity.ok("Logged out successfully");
    }
}