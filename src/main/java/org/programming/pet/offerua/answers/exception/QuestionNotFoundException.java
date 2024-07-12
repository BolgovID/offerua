package org.programming.pet.offerua.answers.exception;

import org.programming.pet.offerua.common.exception.AbstractException;
import org.programming.pet.offerua.common.exception.ErrorCodes;

public class QuestionNotFoundException extends AbstractException {

    public QuestionNotFoundException(String reasonCode, String reasonDescription) {
        super(reasonCode, reasonDescription);
    }

    public QuestionNotFoundException(ErrorCodes errorCodes) {
        super(errorCodes);
    }

    public QuestionNotFoundException(ErrorCodes errorCodes, Object... formatArgs) {
        super(errorCodes, formatArgs);
    }
}
