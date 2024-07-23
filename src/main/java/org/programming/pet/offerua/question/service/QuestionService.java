package org.programming.pet.offerua.question.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.config.CacheConstants;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.question.mapper.QuestionMapper;
import org.programming.pet.offerua.question.persistence.QuestionRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = CacheConstants.QUESTIONS)
public class QuestionService {
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;

    public Page<QuestionDto> findByTopicId(UUID topicId, Pageable pageable) {
        return questionRepository.findByInterviewTopicId(topicId, pageable)
                .map(questionMapper::toDto);
    }

    @Cacheable(key = "#questionId")
    public Optional<QuestionDto> findById(UUID questionId) {
        return questionRepository.findById(questionId)
                .map(questionMapper::toDto);
    }
}
