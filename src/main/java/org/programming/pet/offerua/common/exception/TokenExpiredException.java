package org.programming.pet.offerua.common.exception;

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
