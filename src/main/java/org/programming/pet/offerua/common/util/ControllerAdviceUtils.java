package org.programming.pet.offerua.common.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.exception.AbstractException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.UUID;

@UtilityClass
@Slf4j
public class ControllerAdviceUtils {

    public String prepareDetailMessageBasedOnRole(HttpServletRequest request, String exceptionMessage) {
        if (isUserHasRoleAdmin(request)) {
            return exceptionMessage;
        } else {
            return "For details contact administrator.";
        }

    }

    public ApiErrorResponse createInternalServerError(HttpServletRequest servletRequest, Exception exception) {
        return ApiErrorResponse.builder()
                .id(UUID.randomUUID().toString())
                .code("INTERNAL_SERVER_ERROR")
                .message(prepareDetailMessageBasedOnRole(servletRequest, exception.getLocalizedMessage()))
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(servletRequest.getRequestURI())
                .method(servletRequest.getMethod())
                .errors(Collections.emptyMap())
                .timestamp(TimeUtils.currentTime())
                .build();

    }

    public ApiErrorResponse mapToErrorResponse(HttpStatus httpStatus, AbstractException exception, HttpServletRequest request) {
        return ApiErrorResponse.builder()
                .id(UUID.randomUUID().toString())
                .code(exception.getReasonCode())
                .message(exception.getReasonDescription())
                .statusCode(httpStatus.value())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .errors(Collections.emptyMap())
                .timestamp(TimeUtils.currentTime())
                .build();

    }

    public ApiErrorResponse mapToErrorResponse(HttpStatus httpStatus, String errorCode, MethodArgumentNotValidException exception, HttpServletRequest request) {
        return ApiErrorResponse.builder()
                .id(UUID.randomUUID().toString())
                .code(errorCode)
                .message(exception.getBody().getDetail())
                .statusCode(httpStatus.value())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .errors(ValidationUtils.extractAllValidationErrors(exception))
                .timestamp(TimeUtils.currentTime())
                .build();

    }


    private boolean isUserHasRoleAdmin(HttpServletRequest request) {
        var userFromRequest = RequestUtils.extractTokenFromCookies(request);
        return userFromRequest.isPresent();/*&& userFromRequest.isAdmin())*/
    }
}
