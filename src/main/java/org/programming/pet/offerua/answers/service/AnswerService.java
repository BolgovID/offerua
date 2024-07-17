package org.programming.pet.offerua.answers.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.answers.AnswerDto;
import org.programming.pet.offerua.answers.AnswerFilter;
import org.programming.pet.offerua.answers.AnswersInternalApi;
import org.programming.pet.offerua.answers.mapper.AnswerMapper;
import org.programming.pet.offerua.answers.persistence.AnswerRepository;
import org.programming.pet.offerua.common.config.CacheConstants;
import org.programming.pet.offerua.common.dto.PageResponse;
import org.programming.pet.offerua.common.util.PageableUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = CacheConstants.ANSWERS)
public class AnswerService implements AnswersInternalApi {
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    @Override
    @Cacheable(key = "#questionId")
    public PageResponse<AnswerDto> findAllAnswersByQuestionId(UUID questionId, AnswerFilter paginationRequest) {
        var pageable = PageableUtils.getPageable(paginationRequest);
        var answerDtoPage = answerRepository.findByQuestionId(questionId, pageable)
                .map(answerMapper::toDto);
        return new PageResponse<>(answerDtoPage);
    }
//todo
    @Caching(evict = {
            @CacheEvict(value = CacheConstants.QUESTIONS, key = "#answerDto.questionId"),
            @CacheEvict(value = CacheConstants.ANSWERS, key = "#answerDto.id"),
    })
    public void addAnswer(AnswerDto answerDto) {

    }
}
