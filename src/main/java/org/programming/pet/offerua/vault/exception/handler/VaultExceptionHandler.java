package org.programming.pet.offerua.vault.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ErrorResponse;
import org.programming.pet.offerua.vault.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class VaultExceptionHandler {

    @ExceptionHandler(VerificationTokenExpiredException.class)
    public ResponseEntity<ErrorResponse> handleVerificationTokenExpiredException(VerificationTokenExpiredException ex) {
        log.warn(ex.getMessage());
        var responseStatus = HttpStatus.UNAUTHORIZED;
        var errorResponse = new ErrorResponse(responseStatus.value(), ex.getLocalizedMessage());
        return ResponseEntity
                .status(responseStatus)
                .body(errorResponse);
    }

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<ErrorResponse> handleRefreshTokenExpiredException(RefreshTokenExpiredException ex) {
        log.warn(ex.getMessage());
        var responseStatus = HttpStatus.UNAUTHORIZED;
        var errorResponse = new ErrorResponse(responseStatus.value(), ex.getLocalizedMessage());
        return ResponseEntity
                .status(responseStatus)
                .body(errorResponse);
    }

    @ExceptionHandler(ResetTokenExpiredException.class)
    public ResponseEntity<ErrorResponse> handleResetTokenExpiredException(ResetTokenExpiredException ex) {
        log.warn(ex.getMessage());
        var responseStatus = HttpStatus.UNAUTHORIZED;
        var errorResponse = new ErrorResponse(responseStatus.value(), ex.getLocalizedMessage());
        return ResponseEntity
                .status(responseStatus)
                .body(errorResponse);
    }

    @ExceptionHandler(ResetTokenNotExistException.class)
    public ResponseEntity<ErrorResponse> handleResetTokenNotExistException(ResetTokenNotExistException ex) {
        log.warn(ex.getMessage());
        var responseStatus = HttpStatus.UNAUTHORIZED;
        var errorResponse = new ErrorResponse(responseStatus.value(), ex.getLocalizedMessage());
        return ResponseEntity
                .status(responseStatus)
                .body(errorResponse);
    }

    @ExceptionHandler(VerificationTokenNotExistException.class)
    public ResponseEntity<ErrorResponse> handleVerificationTokenNotExistException(VerificationTokenNotExistException ex) {
        log.warn(ex.getMessage());
        var responseStatus = HttpStatus.UNAUTHORIZED;
        var errorResponse = new ErrorResponse(responseStatus.value(), ex.getLocalizedMessage());
        return ResponseEntity
                .status(responseStatus)
                .body(errorResponse);
    }
}
