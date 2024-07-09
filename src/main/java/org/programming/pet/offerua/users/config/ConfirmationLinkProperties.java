package org.programming.pet.offerua.users.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties("app.security.confirmation-link")
public record ConfirmationLinkProperties(
        Duration expiresIn
) {
}
