package org.programming.pet.offerua.vault.exception;

import org.programming.pet.offerua.common.exception.AbstractException;
import org.programming.pet.offerua.common.exception.ErrorCodes;

public class TokenNotExistException extends AbstractException {

    public TokenNotExistException(ErrorCodes errorCodes, Object... formatArgs) {
        super(errorCodes, formatArgs);
    }

    public TokenNotExistException(ErrorCodes errorCodes) {
        super(errorCodes);
    }

    public TokenNotExistException(String reasonCode, String reasonDescription) {
        super(reasonCode, reasonDescription);
    }
}
