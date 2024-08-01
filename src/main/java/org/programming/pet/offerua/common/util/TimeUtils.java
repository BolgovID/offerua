package org.programming.pet.offerua.common.util;

import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@UtilityClass
public class TimeUtils {

    public Instant computeTimeAfterDuration(Duration duration) {
        return currentTime().plus(duration);
    }

    public Instant currentTime() {
        return Instant.now();
    }

    public Date currentDate() {
        return new Date(currentTime().toEpochMilli());
    }
}
