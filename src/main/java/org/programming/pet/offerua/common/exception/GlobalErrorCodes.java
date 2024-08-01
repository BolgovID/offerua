package org.programming.pet.offerua.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GlobalErrorCodes implements ErrorCodes  {
    INTERNAL_SERVER_ERROR("OFF-UA-001", "Unexpected error"),
    UNAUTHORIZED_ERROR("OFF-UA-002", "Unauthorized error");

    private final String code;
    private final String description;
}
