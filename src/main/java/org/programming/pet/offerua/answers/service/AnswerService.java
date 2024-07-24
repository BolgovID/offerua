package org.programming.pet.offerua.answers.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.answers.AnswerDto;
import org.programming.pet.offerua.answers.mapper.AnswerMapper;
import org.programming.pet.offerua.answers.persistence.AnswerRepository;
import org.programming.pet.offerua.common.dto.CacheConstants;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = CacheConstants.ANSWERS)
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    @Cacheable(key = "#questionId")
    public Page<AnswerDto> findByQuestionId(UUID questionId, Pageable pageable) {
        return answerRepository.findByQuestionId(questionId, pageable)
                .map(answerMapper::toDto);
    }
}
