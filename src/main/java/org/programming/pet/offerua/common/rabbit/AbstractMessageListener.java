package org.programming.pet.offerua.common.rabbit;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.util.LoggerUtils;

import java.io.Serializable;


@RequiredArgsConstructor
public abstract class AbstractMessageListener<T extends Serializable> {
    private final AbstractMessageHandler<T> handler;

    protected void handleReceived(T message, String queue) {
        LoggerUtils.logRabbitDebug("Received Message from queue {}: {}", queue, message);
        handler.handle(message);
    }
}
