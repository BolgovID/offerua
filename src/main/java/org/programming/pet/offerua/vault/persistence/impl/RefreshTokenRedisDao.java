package org.programming.pet.offerua.vault.persistence.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.vault.persistence.RefreshToken;
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

    private static final String KEY_PREFIX = "redirectTo:refresh:";

    @Override
    public RefreshToken save(RefreshToken token, Duration ttl) {
        redisTemplate.opsForHash().put(KEY_PREFIX, token.token(), token);
        redisTemplate.expire(KEY_PREFIX + token.token(), ttl);
        return token;
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        var refreshToken = (RefreshToken) redisTemplate.opsForHash().get(KEY_PREFIX, token);
        return Optional.ofNullable(refreshToken);
    }

    @Override
    public void deleteToken(String token) {
        redisTemplate.opsForHash().delete(KEY_PREFIX, token);
    }
}
