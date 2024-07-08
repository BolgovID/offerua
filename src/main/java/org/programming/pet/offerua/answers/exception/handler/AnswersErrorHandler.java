package org.programming.pet.offerua.answers.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.answers.exception.QuestionNotFoundException;
import org.programming.pet.offerua.common.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class AnswersErrorHandler {

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleQuestionNotFoundException(QuestionNotFoundException ex) {
        log.warn(ex.getMessage());
        var responseStatus = HttpStatus.BAD_REQUEST;
        var errorResponse = new ErrorResponse(responseStatus.value(), ex.getLocalizedMessage());
        return ResponseEntity
                .status(responseStatus)
                .body(errorResponse);
    }
}
