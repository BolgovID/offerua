package org.programming.pet.offerua.topic;

import java.util.List;
import java.util.UUID;

public interface TopicInternalApi {
    List<TopicDto> findAllTopicsByTechId(UUID techId);

    TopicDto findById(UUID topicId);
}
