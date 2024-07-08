package org.programming.pet.offerua.users;

import java.util.Set;

public record UserDto(
        String username,
        String password,
        Set<UserRoleDto> roles
) {
}
