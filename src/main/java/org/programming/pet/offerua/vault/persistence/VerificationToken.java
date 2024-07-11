package org.programming.pet.offerua.vault.persistence;

import lombok.Builder;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.Instant;

@Builder
@RedisHash("verification")
public record VerificationToken(
        String token,
        Instant expirationDate,
        String username
) implements Serializable {
}
