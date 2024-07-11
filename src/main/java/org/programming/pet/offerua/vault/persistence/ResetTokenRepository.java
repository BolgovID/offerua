package org.programming.pet.offerua.vault.persistence;

import java.time.Duration;
import java.util.Optional;

public interface ResetTokenRepository {
    ResetToken save(ResetToken token, Duration ttl);

    Optional<ResetToken> findByToken(String token);

    void deleteToken(String token);
}
