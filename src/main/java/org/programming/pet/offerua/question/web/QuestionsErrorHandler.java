package org.programming.pet.offerua.question.web;

import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.util.ErrorResponseUtils;
import org.programming.pet.offerua.common.util.LoggerUtils;
import org.programming.pet.offerua.question.domain.exception.QuestionNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class QuestionsErrorHandler {

    @ExceptionHandler(QuestionNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleQuestionNotFoundException(QuestionNotFoundException exception) {
        var errorResponse = ErrorResponseUtils.mapToErrorResponse(exception);
        LoggerUtils.logAdviceError(errorResponse.id(), exception);
        return errorResponse;
    }
}