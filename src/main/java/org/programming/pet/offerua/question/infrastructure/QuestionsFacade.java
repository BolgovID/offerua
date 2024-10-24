package org.programming.pet.offerua.question.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.question.QuestionUpdateRequest;
import org.programming.pet.offerua.question.QuestionsExternalApi;
import org.programming.pet.offerua.question.QuestionsInternalApi;
import org.programming.pet.offerua.question.application.QuestionsCommandService;
import org.programming.pet.offerua.question.application.QuestionsQueryService;
import org.programming.pet.offerua.search.QuestionSearchResponse;
import org.programming.pet.offerua.search.QuestionTopicFacet;
import org.programming.pet.offerua.topic.TopicDto;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.programming.pet.offerua.common.dto.CacheConstants.QUESTIONS;

@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = QUESTIONS)
public class QuestionsFacade implements QuestionsExternalApi, QuestionsInternalApi {
    private final QuestionsCommandService commandService;
    private final QuestionsQueryService queryService;

    @Override
    public Long countQuestionsByTopicList(List<TopicDto> topics) {
        return queryService.countQuestionsByTopicIds(topics);
    }

    @Override
    @CacheEvict(allEntries = true)
    public QuestionDto createQuestion(QuestionUpdateRequest questionDto) {
        return commandService.createQuestion(questionDto);
    }

    @Override
    public QuestionSearchResponse findAllQuestions(int size, UUID searchAfterId, String technologyName) {
        return queryService.findAllQuestions(size, searchAfterId, technologyName);
    }

    @Override
    @Cacheable
    public QuestionSearchResponse searchQuestions(
            String text,
            int size,
            UUID searchAfterId,
            List<String> topicFilter,
            String technologyName) {
        return queryService.searchQuestions(text, size, searchAfterId, topicFilter, technologyName);
    }

    @Override
    public List<String> searchQuestionsAutocomplete(String text, int size, String technologyName) {
        return queryService.retrieveQuestionSearchAutocomplete(text, size, technologyName);
    }

    @Override
    public List<QuestionTopicFacet> findQuestionTopic(int size, String technologyName) {
        return queryService.findTechTopics(size, technologyName);
    }
}
