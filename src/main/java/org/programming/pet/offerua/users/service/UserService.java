package org.programming.pet.offerua.users.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.users.UserDto;
import org.programming.pet.offerua.users.mapper.UserMapper;
import org.programming.pet.offerua.users.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserEntity registerUser(UserDto userDto, String password) {
        var userEntity = userMapper.toEntity(userDto);

        roleRepository.findByName(UserRoleName.USER)
                .ifPresent(userEntity.getRoles()::add);

        var encodedPassword = passwordEncoder.encode(password);
        userEntity.setPassword(encodedPassword);

        userEntity.setUserStatus(UserStatus.NOT_CONFIRMED);

        return userRepository.save(userEntity);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existByEmail(String email) {
        return userRepository.findByEmail(email)
                .isPresent();
    }

    public UserEntity confirmUser(UserEntity userEntity) {
        userEntity.setUserStatus(UserStatus.CONFIRMED);
        return userRepository.save(userEntity);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity updatePassword(UserEntity userEntity, String password) {
        var encodedPassword = passwordEncoder.encode(password);
        userEntity.setPassword(encodedPassword);
        return userRepository.save(userEntity);
    }

    public boolean existByUsername(String username) {
        return userRepository.findByUsername(username)
                .isPresent();
    }
}
