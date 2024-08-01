package org.programming.pet.offerua.topic;

import java.io.Serializable;
import java.util.UUID;

public record TopicDto(
        UUID id,
        String name
) implements Serializable {
}
