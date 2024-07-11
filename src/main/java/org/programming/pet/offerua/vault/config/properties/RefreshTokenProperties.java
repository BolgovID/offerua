package org.programming.pet.offerua.vault.config.properties;

import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@ConfigurationProperties("app.security.tokens.refresh")
@Validated
public record RefreshTokenProperties(
        @DurationMin(seconds = 1)
        Duration expiresIn
) {
}
