package org.programming.pet.offerua.search;

import org.programming.pet.offerua.question.QuestionDto;

public interface SearchInternalApi {
    QuestionSearchResultDto searchQuestion(String query);

    void indexQuestion(QuestionDto questionDto);
}
