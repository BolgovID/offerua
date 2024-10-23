package org.programming.pet.offerua.users.domain.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.config.properties.FrontendProperties;
import org.programming.pet.offerua.common.rabbit.message.EmailRedirectMessage;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(FrontendProperties.class)
public class UserEmailMessageFactory {
    private final FrontendProperties frontendProperties;

    public String buildVerificationUrl(String token) {
        return frontendProperties.baseUrl() + frontendProperties.verificationPath() + token;
    }

    public String buildRestoreUrl(String token) {
        return frontendProperties.baseUrl() + frontendProperties.restorePath() + token;
    }

    public EmailRedirectMessage createVerificationMessage(String email, String firstName, String token) {
        var redirectUrl = buildVerificationUrl(token);

        return EmailRedirectMessage.builder()
                .sendTo(email)
                .firstName(firstName)
                .redirectTo(redirectUrl)
                .build();
    }

    public EmailRedirectMessage createResetPasswordMessage(String email, String firstName, String token) {
        var redirectUrl = buildRestoreUrl(token);
        return EmailRedirectMessage.builder()
                .sendTo(email)
                .firstName(firstName)
                .redirectTo(redirectUrl)
                .build();
    }
}
