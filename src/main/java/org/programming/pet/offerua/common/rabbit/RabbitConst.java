package org.programming.pet.offerua.common.rabbit;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RabbitConst {
    public static final String MAILER_EXCHANGE = "mailer.exchange";

    public static final String VERIFICATION_QUEUE = "mail.verification.queue";
    public static final String VERIFICATION_ROUTING_KEY = "mail.verification.routingkey";

    public static final String RESET_PASSWORD_QUEUE = "mail.reset.password.queue";
    public static final String RESET_PASSWORD_ROUTING_KEY = "mail.reset.password.routingkey";
}