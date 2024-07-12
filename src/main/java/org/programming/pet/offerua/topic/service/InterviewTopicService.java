package org.programming.pet.offerua.topic.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.topic.InterviewTopicDto;
import org.programming.pet.offerua.topic.InterviewTopicExternalApi;
import org.programming.pet.offerua.topic.InterviewTopicUpdateRequest;
import org.programming.pet.offerua.topic.exception.InterviewTopicExistException;
import org.programming.pet.offerua.topic.exception.InterviewTopicNotExistException;
import org.programming.pet.offerua.topic.exception.TopicErrorCodes;
import org.programming.pet.offerua.topic.mapper.InterviewTopicMapper;
import org.programming.pet.offerua.topic.persistence.InterviewTopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InterviewTopicService implements InterviewTopicExternalApi {
    private final InterviewTopicRepository interviewTopicRepository;
    private final InterviewTopicMapper interviewTopicMapper;

    @Override
    public InterviewTopicDto createInterviewTopic(InterviewTopicUpdateRequest createTopicRequest) {
        if (interviewTopicRepository.existsByTopicName(createTopicRequest.displayName())) {
            throw new InterviewTopicExistException(TopicErrorCodes.TOPIC_NAME_NOT_UNIQUE, createTopicRequest.topicName());
        }
        if (interviewTopicRepository.existsByTopicDisplayName(createTopicRequest.displayName())) {
            throw new InterviewTopicExistException(TopicErrorCodes.TOPIC_DISPLAY_NAME_NOT_UNIQUE, createTopicRequest.displayName());
        }
        var topicEntity = interviewTopicMapper.toInterviewTopicEntity(createTopicRequest);

        var savedEntity = interviewTopicRepository.save(topicEntity);
        return interviewTopicMapper.toInterviewTopicDto(savedEntity);
    }

    @Override
    public List<InterviewTopicDto> getAllInterviewTopics() {
        return interviewTopicRepository.findAll()
                .stream()
                .map(interviewTopicMapper::toInterviewTopicDto)
                .toList();
    }

    @Override
    public void deleteInterviewTopic(UUID id) {
        var interviewTopicEntity = interviewTopicRepository.findById(id)
                .orElseThrow(() -> new InterviewTopicNotExistException(TopicErrorCodes.TOPIC_NOT_EXIST_BY_ID, id));
        interviewTopicRepository.delete(interviewTopicEntity);
    }
}