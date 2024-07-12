package org.programming.pet.offerua.topic.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.exception.ErrorCodes;

@RequiredArgsConstructor
@Getter
public enum TopicErrorCodes implements ErrorCodes {
    TOPIC_NAME_NOT_UNIQUE("INT-TPC-001", "Topic name %s already exist"),
    TOPIC_DISPLAY_NAME_NOT_UNIQUE("INT-TPC-002", "Topic display name %s already exist"),
    TOPIC_NOT_EXIST_BY_ID("INT-TPC-003", "Topic with id %s not exist"),;
    private final String code;
    private final String description;
}
