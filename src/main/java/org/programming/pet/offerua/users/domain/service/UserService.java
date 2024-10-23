package org.programming.pet.offerua.users.domain.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.users.domain.UserStatus;
import org.programming.pet.offerua.users.domain.entity.UserEntity;
import org.programming.pet.offerua.users.domain.exception.UserExistException;
import org.programming.pet.offerua.users.domain.exception.UserNotExistException;
import org.programming.pet.offerua.users.domain.exception.UsersErrorCodes;
import org.programming.pet.offerua.users.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotExistException(UsersErrorCodes.USERNAME_NOT_EXIST, username));
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotExistException(UsersErrorCodes.USER_EMAIL_NOT_EXIST, email));
    }

    public UserEntity updatePassword(UserEntity userEntity, String password) {
        var encodedPassword = passwordEncoder.encode(password);
        userEntity.setPassword(encodedPassword);
        return userRepository.save(userEntity);
    }

    public void validateEmailUniqueness(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new UserExistException(UsersErrorCodes.USER_EMAIL_NOT_UNIQUE, user.getEmail());
        });
    }

    public void validateUsernameUniqueness(String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            throw new UserExistException(UsersErrorCodes.USERNAME_NOT_UNIQUE, user.getUsername());
        });
    }

    public void verifyUserStatusForResetPassword(UserEntity userEntity) {
        if (userEntity.getUserStatus() == UserStatus.NOT_CONFIRMED) {
            throw new UserNotExistException(UsersErrorCodes.USER_EMAIL_NOT_CONFIRMED, userEntity.getEmail());
        }
    }
}
