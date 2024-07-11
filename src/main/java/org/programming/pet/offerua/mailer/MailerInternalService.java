package org.programming.pet.offerua.mailer;

public interface MailerInternalService {
    void sendVerificationAccountEmail(String frontEndUrl, String mailTo, String dataToSend);

    void sendResetPasswordEmail(String frontEndUrl, String email, String token);
}
