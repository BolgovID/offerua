package org.programming.pet.offerua.vault.service.factory;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.util.TimeUtils;
import org.programming.pet.offerua.vault.persistence.ResetToken;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ResetTokenFactory {

    public ResetToken create(String email, Duration expiration) {
        return new ResetToken(
                UUID.randomUUID().toString(),
                TimeUtils.computeTimeAfterDuration(expiration),
                email
        );
    }
}
