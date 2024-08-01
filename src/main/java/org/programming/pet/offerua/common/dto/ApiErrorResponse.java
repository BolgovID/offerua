package org.programming.pet.offerua.common.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.Map;

@Builder
public record ApiErrorResponse(
        String id,
        String code,
        String message,
        Instant timestamp,
        Map<String, String> errors
) {
}
