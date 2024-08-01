package org.programming.pet.offerua.topic.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.topic.TopicDto;
import org.programming.pet.offerua.topic.UpdateTopicRequest;
import org.programming.pet.offerua.topic.exception.TopicErrorCodes;
import org.programming.pet.offerua.topic.exception.TopicExistException;
import org.programming.pet.offerua.topic.mapper.TopicMapper;
import org.programming.pet.offerua.topic.service.domain.TopicsService;
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
        if (topicsService.existByName(updateTopicRequest.name())) {
            throw new TopicExistException(TopicErrorCodes.TOPIC_NOT_UNIQUE_BY_NAME, updateTopicRequest.name());
        }

        var topic = topicMapper.toEntity(updateTopicRequest, techId);
        var savedTopic = topicsService.save(topic);

        return topicMapper.toDto(savedTopic);
    }
}
