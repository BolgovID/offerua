package org.programming.pet.offerua.topic;

import java.util.UUID;

public record InterviewTopicUpdateRequest(String topicName, String displayName, UUID subtopicId) {
}
