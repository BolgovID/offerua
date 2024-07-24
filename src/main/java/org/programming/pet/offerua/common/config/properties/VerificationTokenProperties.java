package org.programming.pet.offerua.common.config.properties;

import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@ConfigurationProperties("app.security.tokens.verification")
@Validated
public record VerificationTokenProperties(
        @DurationMin(seconds = 1)
        Duration expiresIn,
        String secret,
        String issuer
) {
}
