package org.programming.pet.offerua.users.domain.exception.handler;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.exception.TokenExpiredException;
import org.programming.pet.offerua.common.util.ErrorResponseUtils;
import org.programming.pet.offerua.common.util.LoggerUtils;
import org.programming.pet.offerua.users.domain.exception.UserExistException;
import org.programming.pet.offerua.users.domain.exception.UserNotExistException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UsersErrorHandler extends ResponseEntityExceptionHandler {
    private static final String VALIDATION_ERROR_CODE = "USR-001";
    private static final String ACCESS_DENIED_ERROR_CODE = "USR-001";

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response, ConstraintViolationException exception) throws IOException {
        LoggerUtils.logAdviceError(UUID.randomUUID().toString(), exception);
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AccessDeniedException.class)
    protected ApiErrorResponse handleAccessDeniedException(AccessDeniedException ex) {
        var response = ErrorResponseUtils.mapToErrorResponse(ACCESS_DENIED_ERROR_CODE, ex);
        LoggerUtils.logAdviceError(response.id(), ex);
        return response;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @Nonnull MethodArgumentNotValidException ex,
            @Nonnull HttpHeaders headers,
            @Nonnull HttpStatusCode status,
            @Nonnull WebRequest request
    ) {
        var responseBody = ErrorResponseUtils.mapToErrorResponse(VALIDATION_ERROR_CODE, ex);
        return new ResponseEntity<>(responseBody, headers, status);
    }

    @ResponseBody
    @ExceptionHandler(UserExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleUserExistException(UserExistException ex) {
        var responseBody = ErrorResponseUtils.mapToErrorResponse(ex);
        LoggerUtils.logAdviceError(responseBody.id(), ex);
        return responseBody;
    }

    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleUserNotExistException(UserNotExistException ex) {
        var responseBody = ErrorResponseUtils.mapToErrorResponse(ex);
        LoggerUtils.logAdviceError(responseBody.id(), ex);
        return responseBody;
    }

    @ResponseBody
    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponse handleTokenExpiredException(TokenExpiredException exception) {
        var errorResponse = ErrorResponseUtils.mapToErrorResponse(exception);
        LoggerUtils.logAdviceError(errorResponse.id(), exception);
        return errorResponse;
    }
}