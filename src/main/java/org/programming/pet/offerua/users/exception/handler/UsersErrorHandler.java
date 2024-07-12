package org.programming.pet.offerua.users.exception.handler;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.exception.handler.BaseErrorHandler;
import org.programming.pet.offerua.users.exception.UserExistException;
import org.programming.pet.offerua.users.exception.UserNotExistException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UsersErrorHandler extends BaseErrorHandler {
    private static final String VALIDATION_REASON_CODE = "USR-001";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @Nonnull MethodArgumentNotValidException exception,
            @Nonnull HttpHeaders headers,
            @Nonnull HttpStatusCode status,
            @Nonnull WebRequest request
    ) {
        var errors = extractAllValidationErrors(exception);
        var responseBody = mapToValidationErrorResponse(VALIDATION_REASON_CODE, exception, request, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @ExceptionHandler(UserExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleUserExistException(UserExistException ex, HttpServletRequest request) {
        var responseBody = mapToErrorResponse(HttpStatus.BAD_REQUEST, ex, request);
        logError(responseBody, ex);
        return responseBody;
    }

    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleUserNotExistException(UserNotExistException ex, HttpServletRequest request) {
        var responseBody = mapToErrorResponse(HttpStatus.NOT_FOUND, ex, request);
        logError(responseBody, ex);
        return responseBody;
    }
}