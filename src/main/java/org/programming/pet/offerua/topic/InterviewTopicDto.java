package org.programming.pet.offerua.topic;

import java.io.Serializable;
import java.util.UUID;

public record InterviewTopicDto(
        UUID id,
        String topicName,
        String displayName
) implements Serializable {
}
