package org.programming.pet.offerua.users.domain.exception;

import org.programming.pet.offerua.common.exception.AbstractException;
import org.programming.pet.offerua.common.exception.ErrorCodes;

public class UserNotExistException extends AbstractException {
    public UserNotExistException(String reasonCode, String reasonDescription) {
        super(reasonCode, reasonDescription);
    }

    public UserNotExistException(ErrorCodes errorCodes) {
        super(errorCodes);
    }

    public UserNotExistException(ErrorCodes errorCodes, Object... formatArgs) {
        super(errorCodes, formatArgs);
    }
}
