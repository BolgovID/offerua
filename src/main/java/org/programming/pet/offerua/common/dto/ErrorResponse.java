package org.programming.pet.offerua.common.dto;

public record ErrorResponse(
        int statusCode,
        String message
) {
}
