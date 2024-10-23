package org.programming.pet.offerua.vault.persistence;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public interface VerificationTokenRepository {
    String save(String token, Duration ttl);

    Optional<String> findByToken(String token);

    void deleteToken(String token);
}
