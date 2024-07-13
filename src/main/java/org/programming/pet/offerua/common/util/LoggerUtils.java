package org.programming.pet.offerua.common.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class LoggerUtils {
    private static final String RABBIT_LOG = "RABBIT -> %s";

    public void logRabbitDebug(String template, Object... innerArgs) {
        log.debug(logTemplate(template), innerArgs);
    }

    private String logTemplate(String template) {
        return String.format(RABBIT_LOG, template);
    }
}