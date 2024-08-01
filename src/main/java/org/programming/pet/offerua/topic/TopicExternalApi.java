package org.programming.pet.offerua.topic;

import java.util.UUID;

public interface TopicExternalApi {
    TopicDto createNewTopic(UUID techId, UpdateTopicRequest updateTopicRequest);
}
