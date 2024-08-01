package org.programming.pet.offerua.topic.exception;

import org.programming.pet.offerua.common.exception.AbstractException;
import org.programming.pet.offerua.common.exception.ErrorCodes;

public class TopicNotExistException extends AbstractException {
    public TopicNotExistException(String reasonCode, String reasonDescription) {
        super(reasonCode, reasonDescription);
    }

    public TopicNotExistException(ErrorCodes errorCodes) {
        super(errorCodes);
    }

    public TopicNotExistException(ErrorCodes errorCodes, Object... formatArgs) {
        super(errorCodes, formatArgs);
    }
}
