package org.programming.pet.offerua.vault.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.exception.handler.BaseErrorHandler;
import org.programming.pet.offerua.vault.exception.TokenExpiredException;
import org.programming.pet.offerua.vault.exception.TokenNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class VaultExceptionHandler extends BaseErrorHandler {

    @ExceptionHandler(TokenNotExistException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponse handleTokenNotExistException(TokenNotExistException exception, HttpServletRequest request) {
        var errorResponse = mapToErrorResponse(HttpStatus.UNAUTHORIZED, exception, request);
        logError(errorResponse, exception);
        return errorResponse;
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponse handleTokenExpiredException(TokenExpiredException exception, HttpServletRequest request) {
        var errorResponse = mapToErrorResponse(HttpStatus.UNAUTHORIZED, exception, request);
        logError(errorResponse, exception);
        return errorResponse;
    }
}
