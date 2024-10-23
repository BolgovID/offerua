package org.programming.pet.offerua.mailer.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.rabbit.AbstractMessageHandler;
import org.programming.pet.offerua.common.rabbit.message.EmailRedirectMessage;
import org.programming.pet.offerua.mailer.domain.services.MailerService;
import org.programming.pet.offerua.mailer.domain.services.factory.EmailContentFactory;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResetPasswordHandler implements AbstractMessageHandler<EmailRedirectMessage> {
    private final MailerService mailerService;
    private final EmailContentFactory emailContentFactory;

    @Override
    public void handle(@Validated @Payload EmailRedirectMessage message) {
        log.debug("Creating email containing reset password info");
        var emailContent = emailContentFactory.createRestorePasswordEmailContent(message);
        log.debug("Sending message to {}...", message.sendTo());
        mailerService.sendMimeMessage(message.sendTo(), emailContent);
        log.debug("Message was sent successfully to {}...", message.sendTo());
    }
}