package org.programming.pet.offerua.vault.config.properties;

import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties("app.security.tokens.reset")
public record ResetTokenProperties(
        @DurationMin(seconds = 1)
        Duration expiresIn
) {
}