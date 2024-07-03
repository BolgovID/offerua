package org.programming.pet.offerua.security.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.security.dto.RefreshToken;
import org.programming.pet.offerua.security.repositories.RefreshTokenRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenDao implements RefreshTokenRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String HASH_KEY = "refresh_token:";

    @Override
    public RefreshToken save(RefreshToken token) {
        redisTemplate.opsForHash().put(HASH_KEY, token.token(), token);
        return token;
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        var refreshToken = (RefreshToken) redisTemplate.opsForHash().get(HASH_KEY, token);
        return Optional.ofNullable(refreshToken);
    }

    @Override
    public void deleteToken(String token) {
        redisTemplate.opsForHash().delete(HASH_KEY, token);
    }
}
