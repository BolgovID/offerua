package org.programming.pet.offerua.topic.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.exception.handler.BaseErrorHandler;
import org.programming.pet.offerua.common.util.ControllerAdviceUtils;
import org.programming.pet.offerua.common.util.LoggerUtils;
import org.programming.pet.offerua.topic.exception.InterviewTopicExistException;
import org.programming.pet.offerua.topic.exception.InterviewTopicNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class InterviewTopicExceptionHandler extends BaseErrorHandler {

    @ExceptionHandler(InterviewTopicNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleInterviewTopicNotExistException(InterviewTopicNotExistException exception, HttpServletRequest request) {
        var errorResponse = ControllerAdviceUtils.mapToErrorResponse(HttpStatus.NOT_FOUND, exception, request);
        LoggerUtils.logAdviceError(errorResponse.id(), exception);
        return errorResponse;
    }

    @ExceptionHandler(InterviewTopicExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleInterviewTopicExistException(InterviewTopicExistException exception, HttpServletRequest request) {
        var errorResponse = ControllerAdviceUtils.mapToErrorResponse(HttpStatus.BAD_REQUEST, exception, request);
        LoggerUtils.logAdviceError(errorResponse.id(), exception);
        return errorResponse;
    }
}
