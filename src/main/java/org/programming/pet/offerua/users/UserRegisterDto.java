package org.programming.pet.offerua.users;

public record UserRegisterDto(
        String username,
        String firstName,
        String surname,
        String password,
        String confirmPassword,
        String email
) {
}
