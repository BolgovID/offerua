package org.programming.pet.offerua.search.infrastructure;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.search.QuestionSearchResponse;
import org.programming.pet.offerua.search.QuestionTopicFacet;
import org.programming.pet.offerua.search.SearchInternalApi;
import org.programming.pet.offerua.search.application.SearchQuestionQueryService;
import org.programming.pet.offerua.search.SearchQuestionRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchFacade implements SearchInternalApi {

    private final SearchQuestionQueryService queryService;

    @Override
    public QuestionSearchResponse findAllQuestions(SearchQuestionRequest searchQuestionRequest) {
        return queryService.findAll(searchQuestionRequest);
    }

    @Override
    public QuestionSearchResponse searchQuestions(SearchQuestionRequest searchQuestionRequest) {
        return queryService.search(searchQuestionRequest);
    }

    @Override
    public List<String> searchQuestionsAutocomplete(SearchQuestionRequest searchQuestionRequest) {
        return queryService.autocomplete(searchQuestionRequest);
    }

    @Override
    public List<QuestionTopicFacet> findQuestionFacets(SearchQuestionRequest searchQuestionRequest) {
        return queryService.findAllFacets(searchQuestionRequest);
    }
}
