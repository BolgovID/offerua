package org.programming.pet.offerua.common.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.util.TimeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GenericExceptionHandler extends BaseErrorHandler {


    @ExceptionHandler({Exception.class})
    public ApiErrorResponse handleGenericException(Exception exception, HttpServletRequest request) {
        var apiError = mapToErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, request);
        logError(apiError, exception);
        return apiError;
    }

    private ApiErrorResponse mapToErrorResponse(HttpStatus httpStatus, HttpServletRequest request) {
        return ApiErrorResponse.builder()
                .id(UUID.randomUUID().toString())
                .code("UNKNOWN")
                .message(httpStatus.getReasonPhrase())
                .statusCode(httpStatus.value())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .timestamp(TimeUtils.currentTime())
                .build();
    }
}