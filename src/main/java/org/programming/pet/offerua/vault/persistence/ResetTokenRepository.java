package org.programming.pet.offerua.vault.persistence;

import java.time.Duration;
import java.util.Optional;

public interface ResetTokenRepository {
    String save(String token, Duration ttl);

    Optional<String> findByToken(String token);

    void deleteToken(String token);
}
