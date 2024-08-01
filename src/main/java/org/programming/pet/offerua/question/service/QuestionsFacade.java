package org.programming.pet.offerua.question.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.PaginationRequest;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.question.QuestionUpdateRequest;
import org.programming.pet.offerua.question.QuestionsExternalApi;
import org.programming.pet.offerua.question.QuestionsInternalApi;
import org.programming.pet.offerua.topic.TopicDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionsFacade implements QuestionsExternalApi, QuestionsInternalApi {
    private final QuestionsCommandService commandService;
    private final QuestionsQueryService queryService;

    @Override
    public Long countQuestionsByTopicList(List<TopicDto> topics) {
        return queryService.countQuestionsByTopicIds(topics);
    }

    @Override
    public Page<QuestionDto> findAllQuestionByTopicList(List<TopicDto> topics, PaginationRequest paginationRequest) {
        return queryService.findAllQuestionByTopicList(topics, paginationRequest);
    }

    @Override
    public QuestionDto createQuestion(QuestionUpdateRequest questionDto) {
        return commandService.createQuestion(questionDto);
    }
}
