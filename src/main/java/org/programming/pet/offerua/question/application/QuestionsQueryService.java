package org.programming.pet.offerua.question.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.question.domain.service.QuestionService;
import org.programming.pet.offerua.search.QuestionSearchResponse;
import org.programming.pet.offerua.search.QuestionTopicFacet;
import org.programming.pet.offerua.search.SearchInternalApi;
import org.programming.pet.offerua.search.SearchQuestionRequest;
import org.programming.pet.offerua.topic.TopicDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionsQueryService {
    private final QuestionService questionService;
    private final SearchInternalApi searchInternalApi;

    public Long countQuestionsByTopicIds(List<TopicDto> topics) {
        return topics.stream()
                .map(TopicDto::id)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        questionService::countByTopicIds
                ));
    }

    public QuestionSearchResponse findAllQuestions(int size, UUID searchAfterId, String technologyName) {
        var searchDto = SearchQuestionRequest.builder()
                .text("")
                .size(size)
                .searchAfterId(searchAfterId)
                .technologyName(technologyName)
                .build();
        return searchInternalApi.findAllQuestions(searchDto);
    }

    public QuestionSearchResponse searchQuestions(String text, int size, UUID searchAfterId, List<String> topicFilter, String technologyName) {
        var searchDto = SearchQuestionRequest.builder()
                .text(StringUtils.hasText(text) ? text : "")
                .size(size)
                .searchAfterId(searchAfterId)
                .topicFilter(topicFilter)
                .technologyName(technologyName)
                .build();
        return searchInternalApi.searchQuestions(searchDto);
    }

    public List<String> retrieveQuestionSearchAutocomplete(String text, int size, String technologyName) {
        var searchDto = SearchQuestionRequest.builder()
                .text(StringUtils.hasText(text) ? text : "")
                .size(size)
                .technologyName(technologyName)
                .build();
        return searchInternalApi.searchQuestionsAutocomplete(searchDto);
    }

    public List<QuestionTopicFacet> findTechTopics(int size, String technologyName) {
        var searchDto = SearchQuestionRequest.builder()
                .text("")
                .size(size)
                .technologyName(technologyName)
                .build();
        return searchInternalApi.findQuestionFacets(searchDto);
    }
}