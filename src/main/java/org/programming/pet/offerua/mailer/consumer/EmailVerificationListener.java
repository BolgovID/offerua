package org.programming.pet.offerua.mailer.consumer;

import org.programming.pet.offerua.common.rabbit.AbstractMessageListener;
import org.programming.pet.offerua.common.rabbit.RabbitConst;
import org.programming.pet.offerua.common.rabbit.message.EmailRedirectMessage;
import org.programming.pet.offerua.mailer.consumer.handler.EmailVerificationHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class EmailVerificationListener extends AbstractMessageListener<EmailRedirectMessage> {

    public EmailVerificationListener(EmailVerificationHandler handler) {
        super(handler);
    }

    @RabbitListener(queues = RabbitConst.VERIFICATION_QUEUE)
    public void receive(EmailRedirectMessage message) {
        handleReceived(message, RabbitConst.VERIFICATION_QUEUE);
    }
}
