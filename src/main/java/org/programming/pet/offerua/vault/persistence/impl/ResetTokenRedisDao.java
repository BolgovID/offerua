package org.programming.pet.offerua.vault.persistence.impl;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.vault.persistence.ResetToken;
import org.programming.pet.offerua.vault.persistence.ResetTokenRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ResetTokenRedisDao implements ResetTokenRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String KEY_PREFIX = "redirectTo:reset:";

    @Override
    public ResetToken save(ResetToken token, Duration ttl) {
        redisTemplate.opsForHash().put(KEY_PREFIX, token.token(), token);
        redisTemplate.expire(KEY_PREFIX + token.token(), ttl);
        return token;
    }

    @Override
    public Optional<ResetToken> findByToken(String token) {
        var verificationToken = (ResetToken) redisTemplate.opsForHash().get(KEY_PREFIX, token);
        return Optional.ofNullable(verificationToken);
    }

    @Override
    public void deleteToken(String token) {
        redisTemplate.opsForHash().delete(KEY_PREFIX, token);
    }
}
