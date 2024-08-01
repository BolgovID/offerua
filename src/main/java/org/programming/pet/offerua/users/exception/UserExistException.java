package org.programming.pet.offerua.users.exception;

import org.programming.pet.offerua.common.exception.AbstractException;
import org.programming.pet.offerua.common.exception.ErrorCodes;

public class UserExistException extends AbstractException {
    public UserExistException(String reasonCode, String reasonDescription) {
        super(reasonCode, reasonDescription);
    }

    public UserExistException(ErrorCodes errorCodes) {
        super(errorCodes);
    }

    public UserExistException(ErrorCodes errorCodes, Object... formatArgs) {
        super(errorCodes, formatArgs);
    }
}
