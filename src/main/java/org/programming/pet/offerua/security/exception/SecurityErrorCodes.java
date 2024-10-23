package org.programming.pet.offerua.security.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.exception.ErrorCodes;

@RequiredArgsConstructor
@Getter
public enum SecurityErrorCodes implements ErrorCodes {
    REFRESH_TOKEN_EXPIRED("SEC-REF-001", "Refresh token %s expired at %s");
    private final String code;
    private final String description;
}
