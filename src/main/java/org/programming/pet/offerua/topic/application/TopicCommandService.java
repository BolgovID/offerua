package org.programming.pet.offerua.topic.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.topic.TopicDto;
import org.programming.pet.offerua.topic.UpdateTopicRequest;
import org.programming.pet.offerua.topic.application.mapper.TopicMapper;
import org.programming.pet.offerua.topic.domain.service.TopicsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TopicCommandService {
    private final TopicsService topicsService;
    private final TopicMapper topicMapper;

    public TopicDto createNewTopic(UUID techId, UpdateTopicRequest updateTopicRequest) {
        topicsService.validateNameUniqueness(updateTopicRequest.name());

        var topic = topicMapper.toEntity(updateTopicRequest, techId);
        var savedTopic = topicsService.save(topic);

        return topicMapper.toDto(savedTopic);
    }
}
