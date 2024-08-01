package org.programming.pet.offerua.security.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


@Component
public class LoggingFilter extends OncePerRequestFilter {
    private static final Logger serverLogger = LoggerFactory.getLogger(LoggingFilter.class);
    private static final Logger accessLogger = LoggerFactory.getLogger("access-log");

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain)
            throws ServletException, IOException {

        var requestWrapper = new ContentCachingRequestWrapper(request);
        var responseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(requestWrapper, responseWrapper);
        long timeTaken = System.currentTimeMillis() - startTime;

        var requestBody = getStringValue(requestWrapper.getContentAsByteArray(),
                request.getCharacterEncoding());

        var username = logger.isInfoEnabled() ? extractUsername(requestBody) : "";
        accessLogger.info("Request Method: {},Time taken in ms: {}, User username: {}", request.getMethod(), timeTaken, username);

        responseWrapper.copyBodyToResponse();
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            serverLogger.info("Character Encoding not supported");
        }
        return "";
    }

    private String extractUsername(String body) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var jsonNode = objectMapper.readTree(body);
        return jsonNode.get("username") != null ? jsonNode.get("username").asText() : "";
    }

}