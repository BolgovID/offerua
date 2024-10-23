package org.programming.pet.offerua.common.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.config.properties.AccessTokenProperties;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.dto.UserRoleName;
import org.programming.pet.offerua.common.service.CookieService;
import org.programming.pet.offerua.common.util.ErrorResponseUtils;
import org.programming.pet.offerua.common.util.JwtUtils;
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
@Order(Ordered.LOWEST_PRECEDENCE) //should be there because of handlers priority
@EnableConfigurationProperties(AccessTokenProperties.class)
@RequiredArgsConstructor
public class BaseErrorHandler {
    private final AccessTokenProperties accessTokenProperties;
    private final CookieService cookieService;

    private static final String DEFAULT_INTERNAL_ERROR_MESSAGE = "For details contact administrator.";

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleUnexpectedException(Exception ex, HttpServletRequest request) {
        log.warn("Unexpected internal server error: {}", ex.getMessage());
        ex.printStackTrace();
        var message = prepareDetailMessageBasedOnRole(request, ex.getLocalizedMessage());
        return ErrorResponseUtils.mapToInternalErrorResponse(message);
    }

    private String prepareDetailMessageBasedOnRole(
            HttpServletRequest request,
            String exceptionMessage
    ) {

        return cookieService.getAuthToken(request)
                .map(token -> JwtUtils.extractUserRole(token, accessTokenProperties.secret()))
                .filter(roleList -> roleList.contains(UserRoleName.ADMIN.name()))
                .map(role -> exceptionMessage)
                .orElse(DEFAULT_INTERNAL_ERROR_MESSAGE);
    }
}