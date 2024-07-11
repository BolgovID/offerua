package org.programming.pet.offerua.security.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ErrorResponse;
import org.programming.pet.offerua.vault.exception.RefreshTokenNotExistException;
import org.programming.pet.offerua.security.exception.WebFilterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class SecurityErrorHandler {

    @ExceptionHandler(WebFilterException.class)
    public ResponseEntity<ErrorResponse> handleWebFilterException(WebFilterException ex) {
        log.error(ex.getMessage());
        var responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorResponse = new ErrorResponse(responseStatus.value(), ex.getLocalizedMessage());
        return ResponseEntity
                .status(responseStatus)
                .body(errorResponse);
    }
}