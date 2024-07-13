package org.programming.pet.offerua.mailer.consumer;

import org.programming.pet.offerua.common.rabbit.AbstractMessageListener;
import org.programming.pet.offerua.common.rabbit.RabbitConst;
import org.programming.pet.offerua.common.rabbit.message.EmailRedirectMessage;
import org.programming.pet.offerua.mailer.consumer.handler.ResetPasswordHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ResetPasswordListener extends AbstractMessageListener<EmailRedirectMessage> {
    public ResetPasswordListener(ResetPasswordHandler handler) {
        super(handler);
    }

    @RabbitListener(queues = RabbitConst.RESET_PASSWORD_QUEUE)
    public void receive(EmailRedirectMessage message) {
        handleReceived(message, RabbitConst.RESET_PASSWORD_QUEUE);
    }
}
