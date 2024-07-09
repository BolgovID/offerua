package org.programming.pet.offerua.users;

import java.util.Set;

public record UserAuthDto(
        String username,
        String password,
        Set<UserRoleDto> roles
) {
}
