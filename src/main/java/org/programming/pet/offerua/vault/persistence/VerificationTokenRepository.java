package org.programming.pet.offerua.vault.persistence;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public interface VerificationTokenRepository {
    VerificationToken save(VerificationToken token, Duration ttl);

    Optional<VerificationToken> findByToken(String token);

    void deleteToken(String token);
}
