package org.programming.pet.offerua.security.persistance.impl;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.security.config.properties.RefreshTokenProperties;
import org.programming.pet.offerua.security.persistance.RefreshToken;
import org.programming.pet.offerua.security.persistance.RefreshTokenRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@EnableConfigurationProperties(RefreshTokenProperties.class)
public class RefreshTokenDao implements RefreshTokenRepository {
    private final RedisTemplate<String, Object> redisTemplate;
    private final RefreshTokenProperties refreshTokenProperties;

    private static final String KEY_PREFIX = "token:refresh:";

    @Override
    public RefreshToken save(RefreshToken token) {
        redisTemplate.opsForHash().put(KEY_PREFIX, token.token(), token);
        redisTemplate.expire(KEY_PREFIX + token, refreshTokenProperties.expiresIn());
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
