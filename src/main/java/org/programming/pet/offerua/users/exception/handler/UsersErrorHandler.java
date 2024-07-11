package org.programming.pet.offerua.users.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ErrorResponse;
import org.programming.pet.offerua.users.exception.EmailExistRegisterException;
import org.programming.pet.offerua.users.exception.LinkEncodingException;
import org.programming.pet.offerua.users.exception.LinkExpiredException;
import org.programming.pet.offerua.users.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class UsersErrorHandler {

    @ExceptionHandler(EmailExistRegisterException.class)
    public ResponseEntity<ErrorResponse> handleEmailExistRegisterException(EmailExistRegisterException ex) {
        log.warn(ex.getMessage());
        var responseStatus = HttpStatus.CONFLICT;
        var errorResponse = new ErrorResponse(responseStatus.value(), ex.getLocalizedMessage());
        return ResponseEntity
                .status(responseStatus)
                .body(errorResponse);
    }

    @ExceptionHandler(LinkEncodingException.class)
    public ResponseEntity<ErrorResponse> handleLinkEncodingException(LinkEncodingException ex) {
        log.warn(ex.getMessage());
        var responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorResponse = new ErrorResponse(responseStatus.value(), ex.getLocalizedMessage());
        return ResponseEntity
                .status(responseStatus)
                .body(errorResponse);
    }

    @ExceptionHandler(LinkExpiredException.class)
    public ResponseEntity<ErrorResponse> handleLinkExpiredException(LinkExpiredException ex) {
        log.warn(ex.getMessage());
        var responseStatus = HttpStatus.BAD_REQUEST;
        var errorResponse = new ErrorResponse(responseStatus.value(), ex.getLocalizedMessage());
        return ResponseEntity
                .status(responseStatus)
                .body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        log.warn(ex.getMessage());
        var responseStatus = HttpStatus.NOT_FOUND;
        var errorResponse = new ErrorResponse(responseStatus.value(), ex.getLocalizedMessage());
        return ResponseEntity
                .status(responseStatus)
                .body(errorResponse);
    }
}
