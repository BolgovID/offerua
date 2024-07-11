package org.programming.pet.offerua.users;

public record UserDto(
        String id,
        String username,
        String firstName,
        String surname,
        String email
) {
}
