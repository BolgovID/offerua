package org.programming.pet.offerua.vault.persistence;

import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository {
    String save(String token, Duration ttl);

    Optional<String> findByToken(String token);

    void deleteToken(String token);
}
