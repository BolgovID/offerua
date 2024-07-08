package org.programming.pet.offerua.interview.controller;

import java.util.UUID;

public record InterviewTopicUpdateRequest(String topicName, String displayName, UUID subtopicId) {
}
