package org.programming.pet.offerua.topic.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ErrorResponse;
import org.programming.pet.offerua.topic.exception.DBFieldsUniqueException;
import org.programming.pet.offerua.topic.exception.InterviewTopicNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class InterviewTopicExceptionHandler {

    @ExceptionHandler(DBFieldsUniqueException.class)
    public ResponseEntity<ErrorResponse> handleDBFieldsUniqueException(DBFieldsUniqueException ex) {
        log.warn(ex.getMessage());
        var responseStatus = HttpStatus.CONFLICT;
        var errorResponse = new ErrorResponse(responseStatus.value(), ex.getLocalizedMessage());
        return ResponseEntity
                .status(responseStatus)
                .body(errorResponse);
    }

    @ExceptionHandler(InterviewTopicNotExistException.class)
    public ResponseEntity<ErrorResponse> handleInterviewTopicNotExistException(InterviewTopicNotExistException ex) {
        log.warn(ex.getMessage());
        var responseStatus = HttpStatus.BAD_REQUEST;
        var errorResponse = new ErrorResponse(responseStatus.value(), ex.getLocalizedMessage());
        return ResponseEntity
                .status(responseStatus)
                .body(errorResponse);
    }
}
