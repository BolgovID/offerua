package org.programming.pet.offerua.topic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.topic.service.TopicCommandService;
import org.programming.pet.offerua.topic.service.TopicQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TopicFacade implements TopicInternalApi, TopicExternalApi{
    private final TopicCommandService commandService;
    private final TopicQueryService queryService;

    @Override
    public List<TopicDto> findAllTopicsByTechId(UUID techId) {
        return queryService.findTopicsByTechId(techId);
    }

    @Override
    public TopicDto findById(UUID topicId) {
        return queryService.findById(topicId);
    }

    @Override
    public TopicDto createNewTopic(UUID techId, UpdateTopicRequest updateTopicRequest) {
        return commandService.createNewTopic(techId, updateTopicRequest);
    }

}
