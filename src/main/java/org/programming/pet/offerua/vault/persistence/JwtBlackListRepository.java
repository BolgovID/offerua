package org.programming.pet.offerua.vault.persistence;

import java.time.Duration;

public interface JwtBlackListRepository {
    String save(String token, Duration ttl);

    boolean contain(String token);
}
