package org.programming.pet.offerua.common.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ControllerLoggingAspect {
    private final ObjectMapper objectMapper;

    @Around("within(org.programming.pet.offerua..controller..*) && !within(org.programming.pet.offerua..AuthController)")
    public Object logControllersRequestAndResponse(ProceedingJoinPoint joinPoint) throws Throwable {

        var request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        var requestMethod = request.getMethod();
        var requestURI = request.getRequestURI();

        var args = joinPoint.getArgs();
        var requestBody = parseRequestToJson(args);

        log.info("Received {} {} request: {}", requestMethod, requestURI, requestBody);

        var result = joinPoint.proceed();

        var responseBody = objectMapper.writeValueAsString(result);
        log.info("Return {} {} response: {}", requestMethod, requestURI, responseBody);
        return result;
    }

    private String parseRequestToJson(Object[] args) {
        try {
            return objectMapper.writeValueAsString(args);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert request arguments to JSON", e);
            return "[]";
        }
    }
}