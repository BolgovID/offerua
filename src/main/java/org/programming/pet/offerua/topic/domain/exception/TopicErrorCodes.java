package org.programming.pet.offerua.topic.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.exception.ErrorCodes;

@Getter
@RequiredArgsConstructor
public enum TopicErrorCodes implements ErrorCodes {

    TOPIC_NOT_UNIQUE_BY_NAME("TEC-TOP-001", "Topic name %s already exist"),
    TOPIC_NOT_EXIST_BY_ID("TEC-TOP-002", "Topic with id %s already exist");

    private final String code;
    private final String description;
}
