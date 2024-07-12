package org.programming.pet.offerua.vault.exception;

import org.programming.pet.offerua.common.exception.AbstractException;
import org.programming.pet.offerua.common.exception.ErrorCodes;

public class TokenExpiredException extends AbstractException {

    public TokenExpiredException(String reasonCode, String reasonDescription) {
        super(reasonCode, reasonDescription);
    }

    public TokenExpiredException(ErrorCodes errorCodes) {
        super(errorCodes);
    }

    public TokenExpiredException(ErrorCodes errorCodes, Object... formatArgs) {
        super(errorCodes, formatArgs);
    }
}
