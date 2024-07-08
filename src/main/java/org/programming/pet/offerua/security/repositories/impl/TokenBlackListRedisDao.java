package org.programming.pet.offerua.security.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.security.config.properties.JwtProperties;
import org.programming.pet.offerua.security.repositories.TokenBlacklist;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@EnableConfigurationProperties(JwtProperties.class)
public class TokenBlackListRedisDao implements TokenBlacklist {
    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtProperties jwtProperties;

    private static final String KEY_PREFIX = "token:jwt:blacklist:";

    @Override
    public String addToBlacklist(String token) {
        redisTemplate.opsForHash().put(KEY_PREFIX, token, token);
        redisTemplate.expire(KEY_PREFIX + token, jwtProperties.expiresIn());
        return token;
    }

    @Override
    public boolean isBlacklisted(String token) {
        return redisTemplate.opsForHash().hasKey(KEY_PREFIX, token);
    }

    @Override
    public boolean isNotBlacklisted(String token) {
        return !isBlacklisted(token);
    }
}
