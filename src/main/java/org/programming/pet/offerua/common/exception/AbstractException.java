package org.programming.pet.offerua.common.exception;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@JsonIncludeProperties({"reasonCode", "reasonDescription"})
@Getter
public class AbstractException extends RuntimeException {
    private final String reasonCode;
    private final String reasonDescription;

    public AbstractException(String reasonCode, String reasonDescription) {
        this.reasonCode = reasonCode;
        this.reasonDescription = reasonDescription;
    }

    public AbstractException(ErrorCodes errorCodes) {
        this(errorCodes.getCode(), errorCodes.getDescription());
    }

    public AbstractException(ErrorCodes errorCodes, Object... formatArgs) {
        this(errorCodes.getCode(), errorCodes.getDescription().formatted(formatArgs));
    }
}
