package org.programming.pet.offerua.technologies.exception;

import org.programming.pet.offerua.common.exception.AbstractException;
import org.programming.pet.offerua.common.exception.ErrorCodes;

public class TechnologyExistException extends AbstractException {
    public TechnologyExistException(String reasonCode, String reasonDescription) {
        super(reasonCode, reasonDescription);
    }

    public TechnologyExistException(ErrorCodes errorCodes) {
        super(errorCodes);
    }

    public TechnologyExistException(ErrorCodes errorCodes, Object... formatArgs) {
        super(errorCodes, formatArgs);
    }
}
