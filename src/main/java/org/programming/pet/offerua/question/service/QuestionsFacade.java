package org.programming.pet.offerua.question.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.answers.AnswerFilter;
import org.programming.pet.offerua.answers.AnswersInternalApi;
import org.programming.pet.offerua.common.dto.PageResponse;
import org.programming.pet.offerua.common.util.PageableUtils;
import org.programming.pet.offerua.question.*;
import org.programming.pet.offerua.question.exception.QuestionErrorCodes;
import org.programming.pet.offerua.question.exception.QuestionNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionsFacade implements QuestionsExternalApi, QuestionsInternalApi {
    private final QuestionService questionService;
    private final AnswersInternalApi answersInternalApi;

    @Override
    public PageResponse<QuestionDto> findAllQuestionRelatedToLanguage(UUID topicId, QuestionFilter questionFilterRequest) {
        var pageable = PageableUtils.getPageable(questionFilterRequest);
        var questionDtoPage = questionService.questionCountByTopicId(topicId, pageable);
        return new PageResponse<>(questionDtoPage);
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionWithAnswersDto findAllAnswersByQuestionId(UUID questionId, AnswerFilter paginationRequest) {
        log.info("Search for answers per question by question id: {}", questionId);
        var answers = answersInternalApi.findAnswersByQuestionId(questionId, paginationRequest);
        var question = questionService.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException(QuestionErrorCodes.QUESTION_NOT_FOUND, questionId));

        return QuestionWithAnswersDto.builder()
                .answers(answers)
                .question(question)
                .build();
    }

    @Override
    public long questionCount(UUID topicId) {
        return questionService.questionCountByTopicId(topicId);
    }
}
