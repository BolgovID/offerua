package org.programming.pet.offerua.gateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.users.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/users")
public class UsersController {
    private final UsersExternalApi usersExternalApi;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void requestToRegister(@RequestHeader("origin") String origin, @RequestBody @Validated UserRegisterForm userDto) {
        log.info("Received POST /api/v1/users/register");
        usersExternalApi.requestToRegister(origin, userDto);
    }

    @PutMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto confirmRegistration(@RequestBody String token) {
        log.info("Received PUT /api/v1/users/register");
        return usersExternalApi.confirmRegistration(token);
    }

    @PostMapping("/reset-password")
    @ResponseStatus(HttpStatus.OK)
    public void resetPassword(
            @RequestHeader("origin") String origin,
            @RequestBody EmailConfirmationDto confirmationDto
    ) {
        log.info("Received POST /api/v1/users/reset-password for {}", confirmationDto.email());
        usersExternalApi.requestToResetPassword(origin, confirmationDto.email());
    }

    @PutMapping("/reset-password")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto confirmReset(@RequestBody @Validated UserResetPasswordForm resetPasswordDto) {
        log.info("Received PUT /api/v1/users/reset-password");
        return usersExternalApi.confirmReset(resetPasswordDto);
    }
}
