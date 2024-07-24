package org.programming.pet.offerua.users.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.exception.TokenExpiredException;
import org.programming.pet.offerua.users.*;
import org.programming.pet.offerua.users.exception.UserExistException;
import org.programming.pet.offerua.users.exception.UserNotExistException;
import org.programming.pet.offerua.users.exception.UsersErrorCodes;
import org.programming.pet.offerua.users.mapper.UserMapper;
import org.programming.pet.offerua.users.persistence.UserStatus;
import org.programming.pet.offerua.users.publisher.ResetPasswordPublisher;
import org.programming.pet.offerua.users.publisher.VerificationEmailPublisher;
import org.programming.pet.offerua.vault.VaultInternalApi;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersFacade implements UsersInternalApi, UsersExternalApi {
    private final UserService userService;
    private final UserMapper userMapper;
    private final VaultInternalApi vaultInternalApi;
    private final UserEmailMessageFactory userEmailMessageFactory;
    private final VerificationEmailPublisher verificationEmailPublisher;
    private final ResetPasswordPublisher resetPasswordPublisher;
    private final VerificationTokenService verificationTokenService;
    private final ResetTokenService resetTokenService;

    @Override
    public Optional<UserAuthDto> getUserAuthInfoByUsername(String username) {
        return userService.findByUsername(username)
                .map(userMapper::toAuthDto);
    }

    @Override
    @Transactional
    public void requestToRegister(UserRegisterForm userRegisterForm) {
        if (userService.existByEmail(userRegisterForm.email())) {
            throw new UserExistException(UsersErrorCodes.USER_EMAIL_NOT_UNIQUE, userRegisterForm.email());
        }
        if (userService.existByUsername(userRegisterForm.username())) {
            throw new UserExistException(UsersErrorCodes.USERNAME_NOT_UNIQUE, userRegisterForm.username());
        }
        var userDto = userMapper.toDto(userRegisterForm);
        var userEntity = userService.registerUser(userDto, userRegisterForm.password());

        var token = verificationTokenService.generateToken(userDto.username());
        vaultInternalApi.pushVerificationToken(token);

        var emailMessage = userEmailMessageFactory.createVerificationMessage(
                userEntity.getEmail(),
                userEntity.getFirstName(),
                token
        );
        verificationEmailPublisher.send(emailMessage);
    }

    @Override
    @Transactional
    public UserDto confirmRegistration(String token) {
        var tokenFromVault = vaultInternalApi.popVerificationToken(token);
        if (verificationTokenService.isTokenExpired(tokenFromVault)) {
            throw new TokenExpiredException(UsersErrorCodes.VERIFICATION_TOKEN_EXPIRED, tokenFromVault);
        }
        var username = verificationTokenService.extractUsername(tokenFromVault);

        var userEntity = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotExistException(UsersErrorCodes.USERNAME_NOT_EXIST, username));

        var savedUser = userService.confirmUser(userEntity);
        return userMapper.toDto(savedUser);
    }


    @Override
    public void requestToResetPassword(String email) {
        var userEntity = userService.findByEmail(email)
                .orElseThrow(() -> new UserNotExistException(UsersErrorCodes.USER_EMAIL_NOT_EXIST, email));

        if (userEntity.getUserStatus() == UserStatus.NOT_CONFIRMED) {
            throw new UserNotExistException(UsersErrorCodes.USER_EMAIL_NOT_CONFIRMED, email);
        }

        var token = resetTokenService.generateToken(email);

        vaultInternalApi.pushResetToken(token);

        var emailMessage = userEmailMessageFactory.createResetPasswordMessage(
                email,
                userEntity.getFirstName(),
                token
        );

        resetPasswordPublisher.send(emailMessage);
    }

    @Override
    @Transactional
    public UserDto confirmReset(UserResetPasswordForm resetPasswordDto) {
        var token = vaultInternalApi.popResetToken(resetPasswordDto.token());

        if (resetTokenService.isTokenExpired(token)) {
            throw new TokenExpiredException(UsersErrorCodes.RESET_TOKEN_EXPIRED, token);
        }

        var email = resetTokenService.extractEmail(token);

        var userEntity = userService.findByEmail(email)
                .orElseThrow(() -> new UserNotExistException(UsersErrorCodes.USER_EMAIL_NOT_EXIST, email));

        var savedUser = userService.updatePassword(userEntity, resetPasswordDto.password());
        return userMapper.toDto(savedUser);
    }
}
