package org.programming.pet.offerua.common.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

@UtilityClass
public class AuthHeaderUtils {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String EMPTY_STRING = "";

    public Optional<String> extractTokenFromRequest(HttpServletRequest request) {
        return extractAuthHeaderFromRequest(request)
                .flatMap(AuthHeaderUtils::extractTokenFromAuthHeader);
    }

    public Optional<String> extractAuthHeaderFromRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(AUTHORIZATION_HEADER));
    }

    public Optional<String> extractTokenFromAuthHeader(String authorizationHeader) {
        return isContainBearerToken(authorizationHeader)
                ? Optional.of(authorizationHeader.replace(BEARER_PREFIX, EMPTY_STRING))
                : Optional.empty();
    }

    public boolean isContainBearerToken(String authHeader) {
        return Objects.nonNull(authHeader) && StringUtils.hasText(authHeader) && isBearer(authHeader);
    }

    public boolean isBearer(String header) {
        return header.startsWith(BEARER_PREFIX);
    }
}
