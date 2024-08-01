package org.programming.pet.offerua.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.search.service.QuestionElasticService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ElasticSearchFacade implements SearchInternalApi {
    private final QuestionElasticService questionElasticService;

    @Override
    public QuestionSearchResultDto searchQuestion(String query) {
        log.info("Fetching all questions from Elasticsearch with query: {}", query);
        return questionElasticService.searchByQuestion(query);
    }
}
