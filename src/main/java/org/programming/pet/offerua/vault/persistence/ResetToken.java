package org.programming.pet.offerua.vault.persistence;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.Instant;

@RedisHash("reset")
public record ResetToken(
        String token,
        Instant expiryDate,
        String email
) implements Serializable {
}
