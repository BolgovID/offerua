package org.programming.pet.offerua.topic.exception;

import org.programming.pet.offerua.common.exception.AbstractException;
import org.programming.pet.offerua.common.exception.ErrorCodes;

public class InterviewTopicNotExistException extends AbstractException {

    public InterviewTopicNotExistException(String reasonCode, String reasonDescription) {
        super(reasonCode, reasonDescription);
    }

    public InterviewTopicNotExistException(ErrorCodes errorCodes) {
        super(errorCodes);
    }

    public InterviewTopicNotExistException(ErrorCodes errorCodes, Object... formatArgs) {
        super(errorCodes, formatArgs);
    }
}
