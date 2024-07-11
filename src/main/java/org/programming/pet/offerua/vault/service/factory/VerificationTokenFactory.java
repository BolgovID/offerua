package org.programming.pet.offerua.vault.service.factory;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.util.TimeUtils;
import org.programming.pet.offerua.vault.persistence.VerificationToken;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VerificationTokenFactory {

    public VerificationToken create(String username, Duration expiration) {
        var linkExpiredAt = TimeUtils.computeTimeAfterDuration(expiration);
        var confirmationToken = UUID.randomUUID();
        return new VerificationToken(confirmationToken.toString(), linkExpiredAt, username);
    }
}