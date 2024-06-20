package org.programming.pet.offerua.interview.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.exception.DBFieldsUniqueException;
import org.programming.pet.offerua.interview.dto.InterviewTopicDto;
import org.programming.pet.offerua.interview.dto.InterviewTopicUpdateRequest;
import org.programming.pet.offerua.interview.exception.InterviewTopicNotExistException;
import org.programming.pet.offerua.interview.mapper.InterviewTopicMapper;
import org.programming.pet.offerua.interview.repository.InterviewTopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InterviewTopicService {
    private final InterviewTopicRepository interviewTopicRepository;
    private final InterviewTopicMapper interviewTopicMapper;


    public InterviewTopicDto createInterviewTopic(InterviewTopicUpdateRequest languageCreateRequest) {
        var topicEntity = interviewTopicMapper.toInterviewTopicEntity(languageCreateRequest);
        if (!isTopicUnique(topicEntity.getTopicName(), topicEntity.getTopicDisplayName())) {
            throw new DBFieldsUniqueException("Interview topic already exists");
        }
        var savedEntity = interviewTopicRepository.save(topicEntity);
        return interviewTopicMapper.toInterviewTopicDto(savedEntity);
    }

    public List<InterviewTopicDto> getAllInterviewTopics() {
        return interviewTopicRepository.findAll()
                .stream()
                .map(interviewTopicMapper::toInterviewTopicDto)
                .toList();
    }

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
