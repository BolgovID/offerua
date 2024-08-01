package org.programming.pet.offerua.common.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
@Slf4j
public class LoggerUtils {
    private static final String RABBIT_LOG = "RABBIT -> %s";
    private static final String ERROR_FORMAT = "Error ID=%s; error message: %s";

    public void logRabbitDebug(String template, Object... innerArgs) {
        log.debug(logTemplate(template), innerArgs);
    }

    private String logTemplate(String template) {
        return String.format(RABBIT_LOG, template);
    }

    public void logAdviceError(String errorId, Exception exception) {
        var message = String.format(
                ERROR_FORMAT,
                errorId,
                getFullStackTraceLog(exception)
        );
        log.error(message, exception);
    }

    private String getFullStackTraceLog(Throwable ex) {
        return Arrays.stream(ex.getStackTrace())
                .limit(20)
                .map(Objects::toString)
                .collect(Collectors.joining("\n"));
    }
}