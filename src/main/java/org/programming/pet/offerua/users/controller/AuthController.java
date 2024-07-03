package org.programming.pet.offerua.users.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.security.dto.AuthRequest;
import org.programming.pet.offerua.security.dto.JwtResponseDto;
import org.programming.pet.offerua.security.dto.RefreshTokenRequest;
import org.programming.pet.offerua.users.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public JwtResponseDto authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        log.info("Received POST /login for {}", authRequest.username());
        return authService.authenticate(authRequest.username(), authRequest.password());
    }

    @PostMapping("/refresh-token")
    public JwtResponseDto refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        log.info("Received POST /refresh-token with {}", refreshTokenRequest);
        return authService.refreshToken(refreshTokenRequest.token());
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        log.info("Received POST /logout");
        authService.logout(request);
        return ResponseEntity.ok("Logged out successfully");
    }
}
