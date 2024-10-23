package org.programming.pet.offerua.users.infrastructure.messaging;

import org.programming.pet.offerua.common.rabbit.AbstractMessagePublisher;
import org.programming.pet.offerua.common.dto.RabbitConst;
import org.programming.pet.offerua.common.rabbit.message.EmailRedirectMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class VerificationEmailPublisher extends AbstractMessagePublisher<EmailRedirectMessage> {

    public VerificationEmailPublisher(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate, RabbitConst.MAILER_EXCHANGE, RabbitConst.VERIFICATION_ROUTING_KEY);
    }

    public void send(EmailRedirectMessage redirectEmailMessage) {
        publish(redirectEmailMessage);
    }
}