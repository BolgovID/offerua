package org.programming.pet.offerua.vault.persistence;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.Instant;

@RedisHash("refresh")
public record RefreshToken(
        String token,
        Instant expiryDate,
        String username
) implements Serializable {
}
