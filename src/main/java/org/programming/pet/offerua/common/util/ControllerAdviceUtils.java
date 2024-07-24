package org.programming.pet.offerua.common.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.exception.AbstractException;
import org.programming.pet.offerua.users.persistence.UserRoleName;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@UtilityClass
@Slf4j
public class ControllerAdviceUtils {
    private static final String ACCESS_TOKEN = "access_token";
    private static final String DEFAULT_INTERNAL_ERROR_MESSAGE = "For details contact administrator.";

    public String prepareDetailMessageBasedOnRole(
            HttpServletRequest request,
            String exceptionMessage,
            String secret
    ) {
        return isUserHasRoleAdmin(request, secret) ? exceptionMessage : DEFAULT_INTERNAL_ERROR_MESSAGE;
    }

    public ApiErrorResponse mapToErrorResponse(AbstractException exception) {
        return ApiErrorResponse.builder()
                .id(UUID.randomUUID().toString())
                .code(exception.getReasonCode())
                .message(exception.getReasonDescription())
                .errors(Collections.emptyMap())
                .timestamp(TimeUtils.currentTime())
                .build();

    }

    public ApiErrorResponse mapToErrorResponse(String errorCode, MethodArgumentNotValidException exception) {
        return ApiErrorResponse.builder()
                .id(UUID.randomUUID().toString())
                .code(errorCode)
                .message(exception.getBody().getDetail())
                .errors(ValidationUtils.extractAllValidationErrors(exception))
                .timestamp(TimeUtils.currentTime())
                .build();

    }

    public static ApiErrorResponse mapToErrorResponse(String errorCode, AccessDeniedException exception) {
        return ApiErrorResponse.builder()
                .id(UUID.randomUUID().toString())
                .code(errorCode)
                .message(exception.getLocalizedMessage())
                .errors(Collections.emptyMap())
                .timestamp(TimeUtils.currentTime())
                .build();
    }

    public static ApiErrorResponse mapToInternalErrorResponse(String message) {
        return ApiErrorResponse.builder()
                .id(UUID.randomUUID().toString())
                .code("OUA-INT-001")
                .message(message)
                .errors(Collections.emptyMap())
                .timestamp(TimeUtils.currentTime())
                .build();
    }

    private boolean isUserHasRoleAdmin(HttpServletRequest request, String secret) {
        return Optional.ofNullable(WebUtils.getCookie(request, ACCESS_TOKEN))
                .map(Cookie::getValue)
                .map(token -> JwtUtils.extractSubject(token, secret))
                .filter(UserRoleName.USER.name()::equals)
                .isPresent();
    }
}