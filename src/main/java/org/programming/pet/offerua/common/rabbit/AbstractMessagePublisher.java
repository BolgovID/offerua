package org.programming.pet.offerua.common.rabbit;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.util.LoggerUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.Serializable;

@RequiredArgsConstructor
public abstract class AbstractMessagePublisher<T extends Serializable> {
    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    protected void publish(T message) {
        LoggerUtils.logRabbitDebug("{}: {} Publishing message: {}", exchangeName, routingKey, message);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
