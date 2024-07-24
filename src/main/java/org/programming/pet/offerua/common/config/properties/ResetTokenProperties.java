package org.programming.pet.offerua.common.config.properties;

import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@ConfigurationProperties("app.security.tokens.reset")
@Validated
public record ResetTokenProperties(
        @DurationMin(seconds = 1)
        Duration expiresIn,
        String issuer,
        String secret
) {
}
