package org.programming.pet.offerua.users.application.command;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.config.properties.FrontendProperties;
import org.programming.pet.offerua.common.rabbit.message.EmailRedirectMessage;
import org.programming.pet.offerua.users.UserDto;
import org.programming.pet.offerua.users.domain.service.ResetTokenService;
import org.programming.pet.offerua.users.domain.service.VerificationTokenService;
import org.programming.pet.offerua.vault.VaultInternalApi;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(FrontendProperties.class)

public class EmailCommandService {
    private final FrontendProperties frontendProperties;
    private final VaultInternalApi vaultInternalApi;
    private final VerificationTokenService verificationTokenService;
    private final ResetTokenService resetTokenService;

    public EmailRedirectMessage createVerificationEmail(UserDto userDto) {
        var token = verificationTokenService.generateToken(userDto.username());
        vaultInternalApi.pushVerificationToken(token);

        var redirectUrl = buildVerificationUrl(token);

        return EmailRedirectMessage.builder()
                .sendTo(userDto.email())
                .firstName(userDto.firstName())
                .redirectTo(redirectUrl)
                .build();
    }

    public EmailRedirectMessage createResetPasswordEmail(String email, String firstName) {
        var token = resetTokenService.generateToken(email);
        vaultInternalApi.pushResetToken(token);

        var redirectUrl = buildRestoreUrl(token);
        return EmailRedirectMessage.builder()
                .sendTo(email)
                .firstName(firstName)
                .redirectTo(redirectUrl)
                .build();
    }

    private String buildVerificationUrl(String token) {
        return frontendProperties.baseUrl() + frontendProperties.verificationPath() + token;
    }

    private String buildRestoreUrl(String token) {
        return frontendProperties.baseUrl() + frontendProperties.restorePath() + token;
    }
}
