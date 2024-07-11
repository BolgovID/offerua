package org.programming.pet.offerua.users;


public record UserResetPasswordForm(
        String token,
        String password,
        String confirmPassword
) {
}
