package org.programming.pet.offerua.common.rabbit;

public interface AbstractMessageHandler<T> {
    void handle(T message);
}
