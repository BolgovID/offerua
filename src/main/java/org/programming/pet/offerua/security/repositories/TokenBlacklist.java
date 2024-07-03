package org.programming.pet.offerua.security.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface TokenBlacklist {
    void addToBlacklist(String token);
    boolean isBlacklisted(String token);
}