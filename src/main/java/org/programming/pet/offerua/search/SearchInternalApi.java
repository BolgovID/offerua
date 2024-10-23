package org.programming.pet.offerua.search;

import java.util.List;

public interface SearchInternalApi {
    QuestionSearchResponse findAllQuestions(SearchQuestionRequest searchQuestionRequest);

    QuestionSearchResponse searchQuestions(SearchQuestionRequest searchQuestionRequest);

    List<String> searchQuestionsAutocomplete(SearchQuestionRequest searchQuestionRequest);

    List<QuestionTopicFacet> findQuestionFacets(SearchQuestionRequest searchQuestionRequest);
}
