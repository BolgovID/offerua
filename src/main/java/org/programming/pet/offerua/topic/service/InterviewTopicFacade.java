package org.programming.pet.offerua.topic.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.topic.InterviewTopicDto;
import org.programming.pet.offerua.topic.InterviewTopicExternalApi;
import org.programming.pet.offerua.topic.InterviewTopicUpdateRequest;
import org.programming.pet.offerua.topic.exception.InterviewTopicExistException;
import org.programming.pet.offerua.topic.exception.InterviewTopicNotExistException;
import org.programming.pet.offerua.topic.exception.TopicErrorCodes;
import org.programming.pet.offerua.topic.mapper.InterviewTopicMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InterviewTopicFacade implements InterviewTopicExternalApi {
    private final InterviewTopicService topicService;
    private final InterviewTopicMapper interviewTopicMapper;

    @Override
    public InterviewTopicDto createInterviewTopic(InterviewTopicUpdateRequest createTopicRequest) {
        if (topicService.existsByTopicName(createTopicRequest.displayName())) {
            throw new InterviewTopicExistException(TopicErrorCodes.TOPIC_NAME_NOT_UNIQUE, createTopicRequest.topicName());
        }
        if (topicService.existsByTopicDisplayName(createTopicRequest.displayName())) {
            throw new InterviewTopicExistException(TopicErrorCodes.TOPIC_DISPLAY_NAME_NOT_UNIQUE, createTopicRequest.displayName());
        }
        var topicEntity = interviewTopicMapper.toInterviewTopicEntity(createTopicRequest);

        return topicService.save(topicEntity);
    }

    @Override
    public List<InterviewTopicDto> getAllInterviewTopics() {
        return topicService.findAllTopics();
    }

    @Override
    public InterviewTopicDto deleteInterviewTopic(UUID id) {
        var interviewTopicDto = topicService.findById(id)
                .orElseThrow(() -> new InterviewTopicNotExistException(TopicErrorCodes.TOPIC_NOT_EXIST_BY_ID, id));
        topicService.delete(interviewTopicDto.id());
        return interviewTopicDto;
    }
}