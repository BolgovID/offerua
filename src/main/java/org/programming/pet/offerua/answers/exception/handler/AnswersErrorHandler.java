package org.programming.pet.offerua.answers.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.answers.exception.QuestionNotFoundException;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.exception.handler.BaseErrorHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AnswersErrorHandler extends BaseErrorHandler {

    @ExceptionHandler(QuestionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleQuestionNotFoundException(QuestionNotFoundException exception, HttpServletRequest request) {
        var errorResponse = mapToErrorResponse(HttpStatus.NOT_FOUND, exception, request);
        logError(errorResponse, exception);
        return errorResponse;
    }
}
