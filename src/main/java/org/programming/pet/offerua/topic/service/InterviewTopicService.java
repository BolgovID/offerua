package org.programming.pet.offerua.topic.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.config.CacheConstants;
import org.programming.pet.offerua.topic.InterviewTopicDto;
import org.programming.pet.offerua.topic.mapper.InterviewTopicMapper;
import org.programming.pet.offerua.topic.persistence.InterviewTopicEntity;
import org.programming.pet.offerua.topic.persistence.InterviewTopicRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = CacheConstants.TOPICS)
public class InterviewTopicService {
    private final InterviewTopicRepository interviewTopicRepository;
    private final InterviewTopicMapper interviewTopicMapper;

    public boolean existsByTopicName(String topicName) {
        return interviewTopicRepository.existsByTopicName(topicName);
    }

    public boolean existsByTopicDisplayName(String topicDisplayName) {
        return interviewTopicRepository.existsByTopicDisplayName(topicDisplayName);
    }

    @CacheEvict(key = "#result.id")
    public InterviewTopicDto save(InterviewTopicEntity topicEntity) {
        var savedTopic = interviewTopicRepository.save(topicEntity);
        return interviewTopicMapper.toInterviewTopicDto(savedTopic);
    }

    @Cacheable
    public List<InterviewTopicDto> findAllTopics() {
        return interviewTopicRepository.findAll()
                .stream()
                .map(interviewTopicMapper::toInterviewTopicDto)
                .toList();
    }

    @Cacheable(key = "#topicId")
    public Optional<InterviewTopicDto> findById(UUID topicId) {
        return interviewTopicRepository.findById(topicId)
                .map(interviewTopicMapper::toInterviewTopicDto);
    }

    @CacheEvict(key = "#topicId", beforeInvocation = true)
    public void delete(UUID topicId) {
        interviewTopicRepository.deleteById(topicId);
    }
}
