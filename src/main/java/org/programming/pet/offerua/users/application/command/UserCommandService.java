package org.programming.pet.offerua.users.application.command;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.dto.UserRoleName;
import org.programming.pet.offerua.users.UserDto;
import org.programming.pet.offerua.users.UserRegisterForm;
import org.programming.pet.offerua.users.application.mapper.UserMapper;
import org.programming.pet.offerua.users.domain.UserStatus;
import org.programming.pet.offerua.users.domain.service.ResetTokenService;
import org.programming.pet.offerua.users.domain.service.UserRoleService;
import org.programming.pet.offerua.users.domain.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandService {
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ResetTokenService resetTokenService;

    public UserDto requestToRegister(UserRegisterForm userRegisterForm) {
        userService.validateEmailUniqueness(userRegisterForm.email());
        userService.validateUsernameUniqueness(userRegisterForm.username());

        var userEntity = userMapper.toEntity(userRegisterForm);

        var userRole = userRoleService.findByName(UserRoleName.USER);
        userEntity.getRoles().add(userRole);

        var encodedPassword = passwordEncoder.encode(userRegisterForm.password());
        userEntity.setPassword(encodedPassword);

        userEntity.setUserStatus(UserStatus.NOT_CONFIRMED);
        var savedUser = userService.save(userEntity);
        return userMapper.toDto(savedUser);
    }

    public UserDto confirmRegistration(String username) {
        var userEntity = userService.findByUsername(username);
        userEntity.setUserStatus(UserStatus.CONFIRMED);

        var savedUser = userService.save(userEntity);
        return userMapper.toDto(savedUser);
    }

    public UserDto verifyUserResetPasswordAbility(String email) {
        var userEntity = userService.findByEmail(email);
        userService.verifyUserStatusForResetPassword(userEntity);
        return userMapper.toDto(userEntity);
    }

    public UserDto confirmReset(String token, String password) {
        resetTokenService.verifyExpiration(token);
        var email = resetTokenService.extractEmail(token);
        var userEntity = userService.findByEmail(email);
        var savedUser = userService.updatePassword(userEntity, password);
        return userMapper.toDto(savedUser);
    }
}
