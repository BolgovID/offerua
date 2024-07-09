package org.programming.pet.offerua.gateway;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.security.*;
import org.programming.pet.offerua.users.UserDto;
import org.programming.pet.offerua.users.UserRegisterDto;
import org.programming.pet.offerua.users.UsersExternalApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class AuthController {
    private final SecurityExternalApi securityExternalApi;
    private final UsersExternalApi usersExternalApi;

    @PostMapping("/login")
    public JwtResponseDto authenticateAndGetToken(@RequestBody AuthRequest authRequest, HttpServletResponse servletResponse) {
        log.info("Received POST /login for {}", authRequest.username());
        return securityExternalApi.authenticate(authRequest.username(), authRequest.password(), servletResponse);
    }

    @PostMapping("/refresh-token")
    public JwtResponseDto refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest, HttpServletResponse servletResponse) {
        log.info("Received POST /refresh-token with {}", refreshTokenRequest);
        return securityExternalApi.refreshToken(refreshTokenRequest.token(), servletResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest servletRequest, @RequestBody LogoutRequest logoutRequest) {
        log.info("Received POST /logout");
        securityExternalApi.logout(servletRequest, logoutRequest);
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void requestToRegister(@RequestBody UserRegisterDto userDto) {
        log.info("Received POST /register");
        usersExternalApi.requestToRegister(userDto);
    }

    @PostMapping("/confirm-registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto confirmRegistration(@RequestParam String data) {
        log.info("Received POST /confirm-registration");
        return usersExternalApi.confirmRegistration(data);
    }
}
