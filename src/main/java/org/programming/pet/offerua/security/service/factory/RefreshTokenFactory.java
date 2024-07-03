package org.programming.pet.offerua.security.service.factory;

import org.programming.pet.offerua.security.dto.RefreshToken;
import org.programming.pet.offerua.security.model.UserInfo;
import org.programming.pet.offerua.users.domain.UserEntity;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Component
public class RefreshTokenFactory {

    public RefreshToken create(UserEntity user, Duration duration) {
        return new RefreshToken(
                UUID.randomUUID().toString(),
                Instant.now().plus(duration),
                new UserInfo(user.getUsername())
        );
    }
}
