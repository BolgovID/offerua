package org.programming.pet.offerua.topic.exception;

import org.programming.pet.offerua.common.exception.AbstractException;
import org.programming.pet.offerua.common.exception.ErrorCodes;

public class InterviewTopicExistException extends AbstractException {
    public InterviewTopicExistException(String reasonCode, String reasonDescription) {
        super(reasonCode, reasonDescription);
    }

    public InterviewTopicExistException(ErrorCodes errorCodes) {
        super(errorCodes);
    }

    public InterviewTopicExistException(ErrorCodes errorCodes, Object... formatArgs) {
        super(errorCodes, formatArgs);
    }
}
