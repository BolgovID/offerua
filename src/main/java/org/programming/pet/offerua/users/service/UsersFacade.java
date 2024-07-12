package org.programming.pet.offerua.users.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.mailer.MailerInternalService;
import org.programming.pet.offerua.users.*;
import org.programming.pet.offerua.users.exception.UserExistException;
import org.programming.pet.offerua.users.exception.UserNotExistException;
import org.programming.pet.offerua.users.exception.UsersErrorCodes;
import org.programming.pet.offerua.users.mapper.UserMapper;
import org.programming.pet.offerua.vault.VaultInternalApi;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersFacade implements UsersInternalApi, UsersExternalApi {
    private final UserService userService;
    private final UserMapper userMapper;
    private final TokenParameterEncoder tokenParameterEncoder;
    private final MailerInternalService mailerInternalService;
    private final VaultInternalApi vaultInternalApi;

    @Override
    public Optional<UserAuthDto> getUserAuthInfoByUsername(String username) {
        return userService.findByUsername(username)
                .map(userMapper::toAuthDto);
    }

    @Override
    @Transactional
    public void requestToRegister(String frontEndUrl, UserRegisterForm userRegisterForm) {
        if (userService.existByEmail(userRegisterForm.email())) {
            throw new UserExistException(UsersErrorCodes.USER_EMAIL_NOT_UNIQUE, userRegisterForm.email());
        }
        if (userService.existByUsername(userRegisterForm.username())) {
            throw new UserExistException(UsersErrorCodes.USERNAME_NOT_UNIQUE, userRegisterForm.username());
        }
        var userDto = userMapper.toDto(userRegisterForm);
        var userEntity = userService.registerUser(userDto, userRegisterForm.password());

        var token = vaultInternalApi.generateVerificationToken(userDto.username());
        var encodedToken = tokenParameterEncoder.encodeData(token);
        //todo change to rabbit
        mailerInternalService.sendVerificationAccountEmail(frontEndUrl, userEntity.getEmail(), encodedToken);
    }

    @Override
    @Transactional
    public UserDto confirmRegistration(String encodedToken) {
        var token = tokenParameterEncoder.decode(encodedToken);
        var username = vaultInternalApi.popUsernameByVerificationToken(token);

        var userEntity = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotExistException(UsersErrorCodes.USERNAME_NOT_EXIST, username));

        var savedUser = userService.confirmUser(userEntity);
        return userMapper.toDto(savedUser);
    }

    @Override
    public void requestToResetPassword(String frontEndUrl, String email) {
        if (!userService.existByEmail(email)) {
            throw new UserNotExistException(UsersErrorCodes.USER_EMAIL_NOT_EXIST, email);
        }
        var token = vaultInternalApi.generateResetToken(email);
        var confirmationParameter = tokenParameterEncoder.encodeData(token);
        //todo change to rabbit
        mailerInternalService.sendResetPasswordEmail(frontEndUrl, email, confirmationParameter);
    }

    @Override
    public UserDto confirmReset(UserResetPasswordForm resetPasswordDto) {
        //validation
        var token = tokenParameterEncoder.decode(resetPasswordDto.token());
        var email = vaultInternalApi.popUserEmailByResetToken(token);
        var userEntity = userService.findByEmail(email)
                .orElseThrow(() -> new UserNotExistException(UsersErrorCodes.USER_EMAIL_NOT_EXIST, email));

        var savedUser = userService.updatePassword(userEntity, resetPasswordDto.password());
        return userMapper.toDto(savedUser);
    }
}
