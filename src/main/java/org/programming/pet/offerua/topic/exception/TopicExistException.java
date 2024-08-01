package org.programming.pet.offerua.topic.exception;

import org.programming.pet.offerua.common.exception.AbstractException;
import org.programming.pet.offerua.common.exception.ErrorCodes;

public class TopicExistException extends AbstractException {

    public TopicExistException(String reasonCode, String reasonDescription) {
        super(reasonCode, reasonDescription);
    }

    public TopicExistException(ErrorCodes errorCodes) {
        super(errorCodes);
    }

    public TopicExistException(ErrorCodes errorCodes, Object... formatArgs) {
        super(errorCodes, formatArgs);
    }
}
