package org.programming.pet.offerua.technologies.exception;

import org.programming.pet.offerua.common.exception.AbstractException;
import org.programming.pet.offerua.common.exception.ErrorCodes;

public class TechnologyNotExistException extends AbstractException {

    public TechnologyNotExistException(String reasonCode, String reasonDescription) {
        super(reasonCode, reasonDescription);
    }

    public TechnologyNotExistException(ErrorCodes errorCodes) {
        super(errorCodes);
    }

    public TechnologyNotExistException(ErrorCodes errorCodes, Object... formatArgs) {
        super(errorCodes, formatArgs);
    }
}
