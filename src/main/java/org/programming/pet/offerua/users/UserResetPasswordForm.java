package org.programming.pet.offerua.users;


import org.programming.pet.offerua.common.validator.PasswordsMatches;

@PasswordsMatches
public record UserResetPasswordForm(
        String token,
        String password,
        String confirmPassword
) {
}
