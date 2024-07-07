package org.programming.pet.offerua.security.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.security.repositories.TokenBlacklist;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TokenBlackListDao implements TokenBlacklist {
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String HASH_KEY = "token:jwt:blacklist:";

    @Override
    public void addToBlacklist(String token) {
        redisTemplate.opsForHash().put(HASH_KEY, token, token);
    }

    @Override
    public boolean isBlacklisted(String token) {
        return redisTemplate.opsForHash().hasKey(HASH_KEY, token);
    }
}
