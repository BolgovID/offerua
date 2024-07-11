package org.programming.pet.offerua.vault.persistence;

import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository {
    RefreshToken save(RefreshToken token, Duration ttl);

    Optional<RefreshToken> findByToken(String token);

    void deleteToken(String token);
}
