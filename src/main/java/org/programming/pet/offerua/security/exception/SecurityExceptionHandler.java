package org.programming.pet.offerua.security.exception;

import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.exception.TokenExpiredException;
import org.programming.pet.offerua.common.util.ControllerAdviceUtils;
import org.programming.pet.offerua.common.util.LoggerUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponse handleTokenExpiredException(TokenExpiredException exception) {
        var errorResponse = ControllerAdviceUtils.mapToErrorResponse(exception);
        LoggerUtils.logAdviceError(errorResponse.id(), exception);
        return errorResponse;
    }
}