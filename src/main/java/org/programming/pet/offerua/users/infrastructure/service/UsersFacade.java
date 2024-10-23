package org.programming.pet.offerua.users.infrastructure.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.users.*;
import org.programming.pet.offerua.users.application.command.EmailCommandService;
import org.programming.pet.offerua.users.application.command.UserCommandService;
import org.programming.pet.offerua.users.application.query.UserQueryService;
import org.programming.pet.offerua.users.application.query.VerificationQueryService;
import org.programming.pet.offerua.users.infrastructure.messaging.ResetPasswordPublisher;
import org.programming.pet.offerua.users.infrastructure.messaging.VerificationEmailPublisher;
import org.programming.pet.offerua.vault.VaultInternalApi;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersFacade implements UsersInternalApi, UsersExternalApi {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final EmailCommandService emailCommandService;
    private final VerificationQueryService verificationQueryService;
    private final VaultInternalApi vaultInternalApi;
    private final VerificationEmailPublisher verificationEmailPublisher;
    private final ResetPasswordPublisher resetPasswordPublisher;

    @Override
    public UserAuthDto getUserAuthInfoByUsername(String username) {
        return userQueryService.getUserAuthDtoByUsername(username);
    }

    @Override
    @Transactional
    public void requestToRegister(UserRegisterForm userRegisterForm) {
        var userDto = userCommandService.requestToRegister(userRegisterForm);
        var verificationEmail = emailCommandService.createVerificationEmail(userDto);
        verificationEmailPublisher.send(verificationEmail);
    }

    @Override
    @Transactional
    public UserDto confirmRegistration(String token) {
        var tokenFromVault = vaultInternalApi.popVerificationToken(token);
        var username = verificationQueryService.extractUsername(tokenFromVault);
        return userCommandService.confirmRegistration(username);
    }

    @Override
    public void requestToResetPassword(String email) {
        var userDto = userCommandService.verifyUserResetPasswordAbility(email);
        var emailMessage = emailCommandService.createResetPasswordEmail(userDto.email(), userDto.firstName());
        resetPasswordPublisher.send(emailMessage);
    }

    @Override
    @Transactional
    public UserDto confirmReset(UserResetPasswordForm resetPasswordDto) {
        var token = vaultInternalApi.popResetToken(resetPasswordDto.token());
        return userCommandService.confirmReset(token, resetPasswordDto.password());
    }
}