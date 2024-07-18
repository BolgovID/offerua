package org.programming.pet.offerua.vault.persistence.impl;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.vault.persistence.VerificationTokenRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VerificationTokenRedisDao implements VerificationTokenRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String KEY_PREFIX = "token:verification:";

    @Override
    public String save(String token, Duration ttl) {
        redisTemplate.opsForHash().put(KEY_PREFIX, token, token);
        redisTemplate.expire(KEY_PREFIX + token, ttl);
        return token;
    }

    @Override
    public Optional<String> findByToken(String token) {
        var verificationToken = (String) redisTemplate.opsForHash().get(KEY_PREFIX, token);
        return Optional.ofNullable(verificationToken);
    }

    @Override
    public void deleteToken(String token) {
        redisTemplate.opsForHash().delete(KEY_PREFIX, token);
    }
}