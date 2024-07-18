package org.programming.pet.offerua.common.config.properties;

import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties("app.security.tokens.verification")
public record VerificationTokenProperties(
        @DurationMin(seconds = 1)
        Duration expiresIn,
        String secret,
        String issuer
) {
}
