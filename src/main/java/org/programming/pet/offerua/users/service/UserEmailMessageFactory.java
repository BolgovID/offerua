package org.programming.pet.offerua.users.service;

import org.programming.pet.offerua.common.rabbit.message.EmailRedirectMessage;
import org.springframework.stereotype.Component;

@Component
public class UserEmailMessageFactory {

    public static final String VERIFICATION_PATH = "/auth/verify?data=";
    public static final String RESTORE_PATH = "/auth/restore?data=";

    public String buildVerificationUrl(String baseUrl, String token) {
        return baseUrl + VERIFICATION_PATH + token;
    }

    public String buildRestoreUrl(String baseUrl, String token) {
        return baseUrl + RESTORE_PATH + token;
    }

    public EmailRedirectMessage createVerificationMessage(String email, String firstName, String frontendBaseUrl, String token) {
        var redirectUrl = buildVerificationUrl(frontendBaseUrl, token);

        return EmailRedirectMessage.builder()
                .sendTo(email)
                .firstName(firstName)
                .redirectTo(redirectUrl)
                .build();
    }

    public EmailRedirectMessage createResetPasswordMessage(String email, String firstName, String frontendBaseUrl, String token) {
        var redirectUrl = buildRestoreUrl(frontendBaseUrl, token);
        return EmailRedirectMessage.builder()
                .sendTo(email)
                .firstName(firstName)
                .redirectTo(redirectUrl)
                .build();
    }
}
