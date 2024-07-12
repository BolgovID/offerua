package org.programming.pet.offerua.users.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.exception.handler.BaseErrorHandler;
import org.programming.pet.offerua.users.exception.UserExistException;
import org.programming.pet.offerua.users.exception.UserNotExistException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UsersErrorHandler extends BaseErrorHandler {


    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response, ConstraintViolationException exception) throws IOException {
        logError(UUID.randomUUID().toString(), exception);
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        var responseBody = mapToErrorResponse(HttpStatus.BAD_REQUEST, ex, request);
        logError(responseBody, ex);
        return responseBody;
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