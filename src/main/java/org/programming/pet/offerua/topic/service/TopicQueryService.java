package org.programming.pet.offerua.topic.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.topic.TopicDto;
import org.programming.pet.offerua.topic.mapper.TopicMapper;
import org.programming.pet.offerua.topic.service.domain.TopicsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TopicQueryService {
    private final TopicsService topicsService;
    private final TopicMapper topicMapper;

    public List<TopicDto> findTopicsByTechId(UUID techId) {
        return topicsService.findAllByTechId(techId).stream()
                .map(topicMapper::toDto)
                .toList();
    }

    public TopicDto findById(UUID topicId) {
        var topic = topicsService.findById(topicId);
        return topicMapper.toDto(topic);
    }
}
