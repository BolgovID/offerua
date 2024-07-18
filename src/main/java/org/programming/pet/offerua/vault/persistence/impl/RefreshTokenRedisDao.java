package org.programming.pet.offerua.vault.persistence.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.vault.persistence.RefreshTokenRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenRedisDao implements RefreshTokenRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String KEY_PREFIX = "token:refresh:";

    @Override
    public String save(String token, Duration ttl) {
        redisTemplate.opsForHash().put(KEY_PREFIX, token, token);
        redisTemplate.expire(KEY_PREFIX + token, ttl);
        return token;
    }

    @Override
    public Optional<String> findByToken(String token) {
        var refreshToken = (String) redisTemplate.opsForHash().get(KEY_PREFIX, token);
        return Optional.ofNullable(refreshToken);
    }

    @Override
    public void deleteToken(String token) {
        redisTemplate.opsForHash().delete(KEY_PREFIX, token);
    }
}
