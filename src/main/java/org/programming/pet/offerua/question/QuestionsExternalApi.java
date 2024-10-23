package org.programming.pet.offerua.question;

import org.programming.pet.offerua.search.QuestionSearchResponse;
import org.programming.pet.offerua.search.QuestionTopicFacet;

import java.util.List;
import java.util.UUID;

public interface QuestionsExternalApi {
    QuestionDto createQuestion(QuestionUpdateRequest questionDto);

    QuestionSearchResponse findAllQuestions(int size, UUID searchAfterId, String technologyName);

    QuestionSearchResponse searchQuestions(String text, int size, UUID searchAfterId, List<String> topicFilter, String technologyName);

    List<String> searchQuestionsAutocomplete(String text, int size, String technologyName);

    List<QuestionTopicFacet> findQuestionTopic(int size, String technologyName);
}
