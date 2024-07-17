package org.programming.pet.offerua.common.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ApiErrorResponse;
import org.programming.pet.offerua.common.exception.AbstractException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.UUID;

@UtilityClass
@Slf4j
public class ControllerAdviceUtils {

    public String prepareDetailMessageBasedOnRole(HttpServletRequest request, String exceptionMessage) {
        if (isUserHasRoleAdmin(request)) {
            return exceptionMessage;
        } else {
            return "For details contact administrator.";
        }

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


    private boolean isUserHasRoleAdmin(HttpServletRequest request) {
        var userFromRequest = RequestUtils.extractTokenFromCookies(request);
        return userFromRequest.isPresent();/*&& userFromRequest.isAdmin())*/
    }
}
