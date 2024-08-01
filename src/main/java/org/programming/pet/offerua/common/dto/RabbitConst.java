package org.programming.pet.offerua.common.dto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RabbitConst {
    public static final String DLX_ARG = "x-dead-letter-exchange";
    public static final String DL_ROUTING_ARG = "x-dead-letter-routing-key";

    public static final String MAILER_EXCHANGE = "mailer.exchange";
    public static final String MAILER_DL_EXCHANGE = "mailer.dl.exchange";

    public static final String VERIFICATION_QUEUE = "mail.verification.queue";
    public static final String VERIFICATION_ROUTING_KEY = "mail.verification.routing-key";
    public static final String VERIFICATION_DLQ = "mail.verification.dlq";
    public static final String VERIFICATION_DL_ROUTING_KEY = "mail.verification.dl-routing-key";

    public static final String RESET_PASSWORD_QUEUE = "mail.reset-password.queue";
    public static final String RESET_PASSWORD_ROUTING_KEY = "mail.reset-password.routing-key";
    public static final String RESET_PASSWORD_DLQ = "mail.reset-password.dlq";
    public static final String RESET_PASSWORD_DL_ROUTING_KEY = "mail.reset-password.dl-routing-key";
}