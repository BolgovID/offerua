package org.programming.pet.offerua.common.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@UtilityClass
public class RequestUtils {
    public static final String ACCESS_TOKEN = "access_token";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String EMPTY_STRING = "";

    public Optional<String> extractTokenFromCookies(HttpServletRequest request) {
        return extractCookieValue(request, ACCESS_TOKEN);
    }

    public Optional<String> extractTokenFromHeader(HttpServletRequest request) {
        return extractHeaderValue(request, AUTHORIZATION_HEADER);
    }

    public Optional<String> extractHeaderValue(HttpServletRequest request, String headerName) {
        return extractHeader(request, headerName, RequestUtils::isBearer, header -> header.replace(BEARER_PREFIX, EMPTY_STRING));
    }

    public Optional<String> extractCookieValue(HttpServletRequest request, String cookieName) {
        return extractCookie(request, cookie -> cookie.getName().equals(cookieName), Cookie::getValue);
    }

    private static <T> Optional<T> extractCookie(HttpServletRequest request, Predicate<Cookie> cookieFilter, Function<Cookie, T> mapResolver) {
        return Optional.ofNullable(request.getCookies())
                .stream()
                .flatMap(Arrays::stream)
                .filter(cookieFilter)
                .map(mapResolver)
                .findFirst();
    }

    private static <T> Optional<T> extractHeader(HttpServletRequest request, String headerName, Predicate<String> headerValidator, Function<String, T> mapResolver) {
        return Optional.ofNullable(request.getHeader(headerName))
                .filter(headerValidator)
                .map(mapResolver);
    }

    private boolean isBearer(String header) {
        return header.startsWith(BEARER_PREFIX);
    }

}
