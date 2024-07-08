package org.programming.pet.offerua.security.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.exception.ErrorResponse;
import org.programming.pet.offerua.security.exception.RefreshTokenExpiredException;
import org.programming.pet.offerua.security.exception.RefreshTokenNotExistException;
import org.programming.pet.offerua.security.exception.WebFilterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class SecurityErrorHandler {

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<ErrorResponse> handleRefreshTokenExpiredException(RefreshTokenExpiredException ex) {
        log.warn(ex.getMessage());
        var responseStatus = HttpStatus.UNAUTHORIZED;
        var errorResponse = new ErrorResponse(responseStatus.value(), ex.getLocalizedMessage());
        return ResponseEntity
                .status(responseStatus)
                .body(errorResponse);
    }

    @ExceptionHandler(RefreshTokenNotExistException.class)
    public ResponseEntity<ErrorResponse> handleRefreshTokenNotExistException(RefreshTokenNotExistException ex) {
        log.warn(ex.getMessage());
        var responseStatus = HttpStatus.UNAUTHORIZED;
        var errorResponse = new ErrorResponse(responseStatus.value(), ex.getLocalizedMessage());
        return ResponseEntity
                .status(responseStatus)
                .body(errorResponse);
    }

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
