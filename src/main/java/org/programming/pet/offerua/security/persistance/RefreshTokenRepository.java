package org.programming.pet.offerua.security.persistance;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository {
    RefreshToken save(RefreshToken token);

    Optional<RefreshToken> findByToken(String token);

    void deleteToken(String token);
}
