package org.programming.pet.offerua.interview;

import java.util.List;
import java.util.UUID;

public interface InterviewTopicExternalApi {
    InterviewTopicDto createInterviewTopic(InterviewTopicUpdateRequest topicCreateRequest);

    List<InterviewTopicDto> getAllInterviewTopics();

    void deleteInterviewTopic(UUID id);
}
