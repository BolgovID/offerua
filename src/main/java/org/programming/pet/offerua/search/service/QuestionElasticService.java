package org.programming.pet.offerua.search.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.dto.CacheConstants;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.search.QuestionSearchResultDto;
import org.programming.pet.offerua.search.domain.index.QuestionIndex;
import org.programming.pet.offerua.search.domain.mapper.QuestionIndexMapper;
import org.programming.pet.offerua.search.infrastructure.persistence.QuestionElasticRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = CacheConstants.ES_QUESTION)
@Deprecated
public class QuestionElasticService {
    private final QuestionElasticRepository questionElasticRepository;
    private final QuestionIndexMapper questionIndexMapper;

    @Cacheable(key = "'topics'")
    public QuestionSearchResultDto searchByQuestion(String query) {
        var searchResult = questionElasticRepository.findByQuestion(query)
                .stream()
                .map(QuestionIndex::id)
                .toList();

        return QuestionSearchResultDto.builder()
                .questionIds(searchResult)
                .build();
    }

    public void indexQuestion(QuestionDto questionDto) {
        var questionIndex = questionIndexMapper.toQuestionIndex(questionDto);
        questionElasticRepository.save(questionIndex);
    }

//    public List<QuestionIndex> search(String keywords) {
//        Float nonExistingBoost = null;
//        Query query = QueryBuilders.match("country", keywords, Operator.Or, nonExistingBoost)._toQuery();
//        NativeQuery nativeQuery = NativeQuery.builder().withQuery(query).build();
//        var result = this.elasticsearchOperations.search(nativeQuery, QuestionIndex.class);
//        return result.stream().map(SearchHit::getContent).collect(Collectors.toList());
//    }
}
