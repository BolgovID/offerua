package org.programming.pet.offerua.security.service.factory;

import org.programming.pet.offerua.common.util.TimeUtils;
import org.programming.pet.offerua.security.persistance.RefreshToken;
import org.programming.pet.offerua.security.model.UserInfo;
import org.programming.pet.offerua.users.UserAuthDto;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.UUID;

@Component
public class RefreshTokenFactory {

    public RefreshToken create(UserAuthDto user, Duration duration) {
        return new RefreshToken(
                UUID.randomUUID().toString(),
                TimeUtils.computeTimeAfterDuration(duration),
                new UserInfo(user.username())
        );
    }
}
