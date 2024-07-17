package org.programming.pet.offerua.vault.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.util.ControllerAdviceUtils;
import org.programming.pet.offerua.common.util.LoggerUtils;
import org.programming.pet.offerua.vault.exception.TokenExpiredException;
import org.programming.pet.offerua.vault.exception.TokenNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class VaultExceptionHandler {

    @ResponseBody
    @ExceptionHandler(TokenNotExistException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponse handleTokenNotExistException(TokenNotExistException exception) {
        var errorResponse = ControllerAdviceUtils.mapToErrorResponse(exception);
        LoggerUtils.logAdviceError(errorResponse.id(), exception);
        return errorResponse;
    }

    @ResponseBody
    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponse handleTokenExpiredException(TokenExpiredException exception) {
        var errorResponse = ControllerAdviceUtils.mapToErrorResponse(exception);
        LoggerUtils.logAdviceError(errorResponse.id(), exception);
        return errorResponse;
    }
}
