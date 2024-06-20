package org.programming.pet.offerua.common.exception;

public record ErrorResponse(
        int statusCode,
        String message
) {
}
