package org.programming.pet.offerua.security.dto;

import org.programming.pet.offerua.security.model.UserInfo;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.Instant;

@RedisHash("Token")
public record RefreshToken(
        String token,
        Instant expiryDate,
        UserInfo user
) implements Serializable {
}
