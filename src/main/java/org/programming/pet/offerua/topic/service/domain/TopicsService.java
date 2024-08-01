package org.programming.pet.offerua.topic.service.domain;


import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.topic.exception.TopicErrorCodes;
import org.programming.pet.offerua.topic.exception.TopicNotExistException;
import org.programming.pet.offerua.topic.persistence.TopicEntity;
import org.programming.pet.offerua.topic.persistence.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TopicsService {
    private final TopicRepository topicRepository;

    public boolean existByName(String name) {
        return topicRepository.existsByName(name);
    }

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
}
