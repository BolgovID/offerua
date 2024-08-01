package org.programming.pet.offerua.search.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.dto.CacheConstants;
import org.programming.pet.offerua.search.QuestionSearchResultDto;
import org.programming.pet.offerua.search.persistence.InterviewQuestion;
import org.programming.pet.offerua.search.persistence.QuestionElasticRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = CacheConstants.ES_QUESTION)
public class QuestionElasticService {
    private final QuestionElasticRepository questionElasticRepository;

    @Cacheable(key = "'topics'")
    public QuestionSearchResultDto searchByQuestion(String query) {
        var searchResult = questionElasticRepository.findByQuestion(query)
                .stream()
                .map(InterviewQuestion::id)
                .toList();

        return QuestionSearchResultDto.builder()
                .questionIds(searchResult)
                .build();
    }
}
