package org.programming.pet.offerua.vault.service.factory;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.util.TimeUtils;
import org.programming.pet.offerua.vault.persistence.RefreshToken;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RefreshTokenFactory {

    public RefreshToken create(String username, Duration expiration) {
        return new RefreshToken(
                UUID.randomUUID().toString(),
                TimeUtils.computeTimeAfterDuration(expiration),
                username
        );
    }
}
