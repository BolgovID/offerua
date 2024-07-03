package org.programming.pet.offerua.security.config.properties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@ConfigurationProperties("app.security.jwt")
@Validated
public record JwtProperties(
        @NotNull
        String secret,
        @NotEmpty
        String issuer,
        @DurationMin(seconds = 1)
        Duration expiresIn
) {
}
