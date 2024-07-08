package org.programming.pet.offerua.users;

import java.util.Optional;

public interface UsersInternalApi {
    Optional<UserDto> getUserByUsername(String username);
}
