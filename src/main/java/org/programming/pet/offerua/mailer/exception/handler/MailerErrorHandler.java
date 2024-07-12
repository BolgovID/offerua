package org.programming.pet.offerua.mailer.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ErrorResponse;
import org.programming.pet.offerua.mailer.exception.SendMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@Slf4j
public class MailerErrorHandler {

    @ExceptionHandler(SendMessageException.class)
    public ResponseEntity<ErrorResponse> handleSendMessageException(SendMessageException ex) {
        log.warn(ex.getMessage());
        var responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorResponse = new ErrorResponse(responseStatus.value(), ex.getLocalizedMessage());
        return ResponseEntity
                .status(responseStatus)
                .body(errorResponse);
    }
}
