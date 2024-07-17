package org.programming.pet.offerua.mailer.consumer;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.rabbit.AbstractMessageListener;
import org.programming.pet.offerua.common.rabbit.RabbitConst;
import org.programming.pet.offerua.common.rabbit.message.EmailRedirectMessage;
import org.programming.pet.offerua.mailer.consumer.handler.EmailVerificationHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class EmailVerificationListener extends AbstractMessageListener<EmailRedirectMessage> {

    public EmailVerificationListener(EmailVerificationHandler handler) {
        super(handler);
    }

    @RabbitListener(queues = RabbitConst.VERIFICATION_QUEUE)
    public void receive(@Payload @Valid EmailRedirectMessage message) {
        try {
            handleReceived(message, RabbitConst.VERIFICATION_QUEUE);
        } catch (Exception e) {
            log.error("Error while sending message to {}. Trying again...", message.sendTo());
            throw e;
        }
    }
}