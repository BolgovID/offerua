package org.programming.pet.offerua.common.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("app.front-end")
@Validated
public record FrontendProperties(
        String baseUrl,
        String verificationPath,
        String restorePath
) {
}
