package org.programming.pet.offerua.technologies.web;

import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.util.ErrorResponseUtils;
import org.programming.pet.offerua.common.util.LoggerUtils;
import org.programming.pet.offerua.technologies.domain.exception.TechnologyExistException;
import org.programming.pet.offerua.technologies.domain.exception.TechnologyNotExistException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TechnologyExceptionHandler {

    @ExceptionHandler(TechnologyNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleInterviewTopicNotExistException(TechnologyNotExistException exception) {
        var errorResponse = ErrorResponseUtils.mapToErrorResponse(exception);
        LoggerUtils.logAdviceError(errorResponse.id(), exception);
        return errorResponse;
    }

    @ExceptionHandler(TechnologyExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleInterviewTopicExistException(TechnologyExistException exception) {
        var errorResponse = ErrorResponseUtils.mapToErrorResponse(exception);
        LoggerUtils.logAdviceError(errorResponse.id(), exception);
        return errorResponse;
    }
}
