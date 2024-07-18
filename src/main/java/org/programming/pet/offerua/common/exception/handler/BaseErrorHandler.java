package org.programming.pet.offerua.common.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.config.properties.AccessTokenProperties;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.util.ControllerAdviceUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties(AccessTokenProperties.class)
@RequiredArgsConstructor
public class BaseErrorHandler {
    private final AccessTokenProperties accessTokenProperties;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleUnexpectedException(Exception ex, HttpServletRequest request) {
        log.warn("Unexpected internal server error: {}", ex.getMessage());
        var message = ControllerAdviceUtils.prepareDetailMessageBasedOnRole(request, ex.getLocalizedMessage(), accessTokenProperties.secret());
        return ControllerAdviceUtils.mapToInternalErrorResponse(message);
    }
}