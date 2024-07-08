package org.programming.pet.offerua.users.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.users.UserDto;
import org.programming.pet.offerua.users.UsersInternalApi;
import org.programming.pet.offerua.users.mapper.UserMapper;
import org.programming.pet.offerua.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService implements UsersInternalApi {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<UserDto> getUserByUsername(String username) {
        return userRepository.getByUsername(username)
                .map(userMapper::toDto);
    }
}
