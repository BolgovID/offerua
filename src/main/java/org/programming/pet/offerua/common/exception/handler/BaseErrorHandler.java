package org.programming.pet.offerua.common.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.exception.AbstractException;
import org.programming.pet.offerua.common.util.TimeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestControllerAdvice
@Slf4j
public class BaseErrorHandler extends ResponseEntityExceptionHandler {
    private static final String ERROR_FORMAT = "Error ID=%s; error message: %s";
    private static final String DEFAULT_VALIDATION_ERROR_MESSAGE = "Invalid input";

    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response, ConstraintViolationException exception) throws IOException {
        logError(UUID.randomUUID().toString(), exception);
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    protected void logError(String errorId, Exception exception) {
        var message = String.format(
                ERROR_FORMAT,
                errorId,
                getFullStackTraceLog(exception)
        );
        log.error(message, exception);
    }

    protected void logError(ApiErrorResponse errorResponse, Exception exception) {
        logError(errorResponse.id(), exception);
    }

    protected Map<String, String> extractAllValidationErrors(MethodArgumentNotValidException exception) {
        var fieldErrors = extractFieldValidationErrors(exception);
        var globalMessage = extractGlobalValidationErrors(exception);
        return Stream.concat(globalMessage.entrySet().stream(), fieldErrors.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    protected Map<String, String> extractFieldValidationErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, this::getDefaultMessage));
    }

    protected Map<String, String> extractGlobalValidationErrors(MethodArgumentNotValidException exception) {
        return exception.getGlobalErrors()
                .stream()
                .collect(Collectors.toMap(ObjectError::getObjectName, this::getDefaultMessage));
    }

    protected ApiErrorResponse mapToErrorResponse(HttpStatus httpStatus, AbstractException exception, HttpServletRequest request) {
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

    protected ApiErrorResponse mapToValidationErrorResponse(String reasonCode, MethodArgumentNotValidException exception, WebRequest webRequest, Map<String, String> errors) {
        var servletRequest = ((ServletWebRequest) webRequest).getRequest();
        return ApiErrorResponse.builder()
                .id(UUID.randomUUID().toString())
                .code(reasonCode)
                .message(exception.getBody().getDetail())
                .statusCode(exception.getStatusCode().value())
                .method(servletRequest.getMethod())
                .path(servletRequest.getRequestURI())
                .errors(errors)
                .timestamp(TimeUtils.currentTime())
                .build();
    }

    protected String getDefaultMessage(FieldError fieldError) {
        return Optional.ofNullable(fieldError.getDefaultMessage())
                .orElse(DEFAULT_VALIDATION_ERROR_MESSAGE);
    }

    protected String getDefaultMessage(ObjectError objectError) {
        return Optional.ofNullable(objectError.getDefaultMessage())
                .orElse(DEFAULT_VALIDATION_ERROR_MESSAGE);
    }

    protected String getFullStackTraceLog(Exception ex) {
        return Arrays.stream(ex.getStackTrace())
                .limit(20)
                .map(Objects::toString)
                .collect(Collectors.joining("\n"));
    }
}
