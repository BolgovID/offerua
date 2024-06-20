package org.programming.pet.offerua.common.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.exception.DBFieldsUniqueException;
import org.programming.pet.offerua.common.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DBFieldsUniqueException.class)
    public ResponseEntity<ErrorResponse> handleDBFieldsUniqueException(DBFieldsUniqueException ex) {
        log.error(ex.getMessage());
        var errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
