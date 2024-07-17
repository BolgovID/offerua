package org.programming.pet.offerua.common.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
@ResponseBody
@Order(Ordered.LOWEST_PRECEDENCE)
public class BaseErrorHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleMethodArgumentNotValidException(Exception ex) {
        log.warn("Invalid user input: {}", ex.getMessage());
        return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
    }
}