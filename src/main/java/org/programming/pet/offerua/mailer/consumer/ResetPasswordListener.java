package org.programming.pet.offerua.mailer.consumer;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.rabbit.AbstractMessageListener;
import org.programming.pet.offerua.common.dto.RabbitConst;
import org.programming.pet.offerua.common.rabbit.message.EmailRedirectMessage;
import org.programming.pet.offerua.mailer.consumer.handler.ResetPasswordHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResetPasswordListener extends AbstractMessageListener<EmailRedirectMessage> {
    public ResetPasswordListener(ResetPasswordHandler handler) {
        super(handler);
    }

    @RabbitListener(queues = RabbitConst.RESET_PASSWORD_QUEUE)
    public void receive(@Valid @Payload EmailRedirectMessage message) {
        try {
            handleReceived(message, RabbitConst.RESET_PASSWORD_QUEUE);
        } catch (Exception e) {
            log.error("Error while sending message to {}. Trying again...", message.sendTo());
            throw e;
        }
    }
}
