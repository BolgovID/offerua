package org.programming.pet.offerua.interview.dto;

import java.util.UUID;

public record InterviewTopicUpdateRequest(String topicName, String displayName, UUID subtopicId) {
}
