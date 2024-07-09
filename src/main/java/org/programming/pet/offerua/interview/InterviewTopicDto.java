package org.programming.pet.offerua.interview;

import java.io.Serializable;
import java.util.UUID;

public record InterviewTopicDto(
        UUID id,
        String topicName,
        String displayName
) implements Serializable {
}
