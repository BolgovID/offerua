package org.programming.pet.offerua.vault.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.util.ErrorResponseUtils;
import org.programming.pet.offerua.vault.exception.TokenNotExistException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class VaultExceptionHandler {

    @ResponseBody
    @ExceptionHandler(TokenNotExistException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponse handleTokenNotExistException(TokenNotExistException exception) {
        return ErrorResponseUtils.mapToErrorResponse(exception);
    }

}
