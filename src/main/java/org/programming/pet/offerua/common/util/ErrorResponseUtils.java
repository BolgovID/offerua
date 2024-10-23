package org.programming.pet.offerua.common.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.exception.AbstractException;
import org.programming.pet.offerua.common.exception.GlobalErrorCodes;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.UUID;

@UtilityClass
@Slf4j
public class ErrorResponseUtils {

    public ApiErrorResponse mapToErrorResponse(AbstractException exception) {
        return ApiErrorResponse.builder()
                .id(UUID.randomUUID().toString())
                .code(exception.getReasonCode())
                .message(exception.getReasonDescription())
                .errors(Collections.emptyMap())
                .timestamp(TimeUtils.currentTime())
                .build();

    }

    public ApiErrorResponse mapToErrorResponse(String errorCode, MethodArgumentNotValidException exception) {
        return ApiErrorResponse.builder()
                .id(UUID.randomUUID().toString())
                .code(errorCode)
                .message(exception.getBody().getDetail())
                .errors(ValidationUtils.extractAllValidationErrors(exception))
                .timestamp(TimeUtils.currentTime())
                .build();

    }

    public ApiErrorResponse mapToErrorResponse(String errorCode, AccessDeniedException exception) {
        return ApiErrorResponse.builder()
                .id(UUID.randomUUID().toString())
                .code(errorCode)
                .message(exception.getLocalizedMessage())
                .errors(Collections.emptyMap())
                .timestamp(TimeUtils.currentTime())
                .build();
    }

    public ApiErrorResponse mapToInternalErrorResponse(String message) {
        return ApiErrorResponse.builder()
                .id(UUID.randomUUID().toString())
                .code(GlobalErrorCodes.INTERNAL_SERVER_ERROR.getCode())
                .message(message)
                .errors(Collections.emptyMap())
                .timestamp(TimeUtils.currentTime())
                .build();
    }

    public ApiErrorResponse mapToUnauthorizedResponse(String message) {
        return ApiErrorResponse.builder()
                .id(UUID.randomUUID().toString())
                .code(GlobalErrorCodes.UNAUTHORIZED_ERROR.getCode())
                .message(message)
                .timestamp(TimeUtils.currentTime())
                .errors(Collections.emptyMap())
                .build();
    }
}