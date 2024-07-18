package org.programming.pet.offerua.vault.persistence.impl;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.config.properties.AccessTokenProperties;
import org.programming.pet.offerua.vault.persistence.JwtBlackListRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
@EnableConfigurationProperties(AccessTokenProperties.class)
public class JwtBlackListRedisDao implements JwtBlackListRepository {
    private final RedisTemplate<String, Object> redisTemplate;
    private final AccessTokenProperties accessTokenProperties;

    private static final String KEY_PREFIX = "token:jwt:blacklist:";

    @Override
    public String save(String token, Duration ttl) {
        redisTemplate.opsForHash()
                .put(KEY_PREFIX, token, token);
        redisTemplate.expire(KEY_PREFIX + token, accessTokenProperties.expiresIn());
        return token;
    }

    @Override
    public boolean contain(String token) {
        return redisTemplate.opsForHash()
                .hasKey(KEY_PREFIX, token);
    }
}
