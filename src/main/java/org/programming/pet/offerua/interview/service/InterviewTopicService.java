package org.programming.pet.offerua.interview.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.interview.InterviewTopicDto;
import org.programming.pet.offerua.interview.InterviewTopicExternalApi;
import org.programming.pet.offerua.interview.InterviewTopicUpdateRequest;
import org.programming.pet.offerua.interview.exception.DBFieldsUniqueException;
import org.programming.pet.offerua.interview.exception.InterviewTopicNotExistException;
import org.programming.pet.offerua.interview.mapper.InterviewTopicMapper;
import org.programming.pet.offerua.interview.persistence.InterviewTopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InterviewTopicService implements InterviewTopicExternalApi {
    private final InterviewTopicRepository interviewTopicRepository;
    private final InterviewTopicMapper interviewTopicMapper;

    @Override
    public InterviewTopicDto createInterviewTopic(InterviewTopicUpdateRequest languageCreateRequest) {
        var topicEntity = interviewTopicMapper.toInterviewTopicEntity(languageCreateRequest);
        if (!isTopicUnique(topicEntity.getTopicName(), topicEntity.getTopicDisplayName())) {
            throw new DBFieldsUniqueException();
        }
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
                .orElseThrow(() -> new InterviewTopicNotExistException(id));
        interviewTopicRepository.delete(interviewTopicEntity);
    }

    private boolean isTopicUnique(String topicName, String displayName) {
        return !interviewTopicRepository.existsByTopicDisplayName(displayName)
                && !interviewTopicRepository.existsByTopicName(topicName);
    }
}
