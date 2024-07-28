package org.programming.pet.offerua.topic;

import java.util.List;
import java.util.UUID;

public interface InterviewTopicExternalApi {
    InterviewTopicDto createInterviewTopic(InterviewTopicUpdateRequest topicCreateRequest);

    List<TopicWithQuestionCountDto> getAllInterviewTopics();

    InterviewTopicDto deleteInterviewTopic(UUID id);
}
