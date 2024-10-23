package org.programming.pet.offerua.topic.domain.service;


import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.topic.domain.entities.TopicEntity;
import org.programming.pet.offerua.topic.domain.exception.TopicErrorCodes;
import org.programming.pet.offerua.topic.domain.exception.TopicExistException;
import org.programming.pet.offerua.topic.domain.exception.TopicNotExistException;
import org.programming.pet.offerua.topic.domain.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TopicsService {
    private final TopicRepository topicRepository;

    public TopicEntity save(TopicEntity topic) {
        return topicRepository.save(topic);
    }

    public List<TopicEntity> findAllByTechId(UUID techId) {
        return topicRepository.findAllByTechnologyId(techId);
    }

    public TopicEntity findById(UUID topicId) {
        return topicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotExistException(TopicErrorCodes.TOPIC_NOT_EXIST_BY_ID, topicId));
    }

    public void validateNameUniqueness(String name) {
        topicRepository.findByName(name).ifPresent(topic -> {
            throw new TopicExistException(TopicErrorCodes.TOPIC_NOT_UNIQUE_BY_NAME, topic.getName());
        });
    }
}
