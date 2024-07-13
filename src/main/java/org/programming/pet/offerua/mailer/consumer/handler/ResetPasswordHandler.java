package org.programming.pet.offerua.mailer.consumer.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.rabbit.AbstractMessageHandler;
import org.programming.pet.offerua.common.rabbit.message.EmailRedirectMessage;
import org.programming.pet.offerua.mailer.exception.SendMessageException;
import org.programming.pet.offerua.mailer.services.EmailTemplateEngine;
import org.programming.pet.offerua.mailer.services.MailerService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResetPasswordHandler implements AbstractMessageHandler<EmailRedirectMessage> {
    private final EmailTemplateEngine emailTemplateEngine;
    private final MailerService mailerService;
    @Override
    public void handle(EmailRedirectMessage message) {
        try {
            log.debug("Creating email containing reset password info");
            log.debug("Sending message to {}...", message.sendTo());
            var emailTemplate = emailTemplateEngine.toRestorePasswordTemplate(message);
            log.debug("Sending message...");
            mailerService.sendMimeMessage(message.sendTo(), "Verify your email address", emailTemplate);
            log.debug("Message was sent successfully to {}...", message.sendTo());
        } catch (SendMessageException exception) {
            log.error("Message {} return back to queue due to exception while sending email", message);
        }
    }
}
