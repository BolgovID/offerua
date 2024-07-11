package org.programming.pet.offerua.gateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.users.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/users")
public class UsersController {
    private final UsersExternalApi usersExternalApi;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void requestToRegister(@RequestHeader("origin") String origin, @RequestBody UserRegisterForm userDto) {
        log.info("Received POST /api/v1/users/register");
        usersExternalApi.requestToRegister(origin, userDto);
    }

    @PutMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto confirmRegistration(@RequestBody String data) {
        log.info("Received PUT /api/v1/users/register");
        return usersExternalApi.confirmRegistration(data);
    }

    @PostMapping("/reset-password")
    @ResponseStatus(HttpStatus.OK)
    public void resetPassword(
            @RequestHeader("origin") String origin,
            @RequestBody UserEmailDto emailDto
    ) {
        log.info("Received POST /api/v1/users/reset-password for {}", emailDto);
        usersExternalApi.requestToResetPassword(origin, emailDto.email());
    }

    @PutMapping("/reset-password")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto confirmReset(@RequestBody UserResetPasswordForm resetPasswordDto) {
        log.info("Received PUT /api/v1/users/reset-password");
        return usersExternalApi.confirmReset(resetPasswordDto);
    }
}
