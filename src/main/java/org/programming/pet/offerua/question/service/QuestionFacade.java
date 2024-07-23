package org.programming.pet.offerua.question.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.answers.AnswerFilter;
import org.programming.pet.offerua.answers.AnswersInternalApi;
import org.programming.pet.offerua.common.dto.PageResponse;
import org.programming.pet.offerua.common.util.PageableUtils;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.question.QuestionFilter;
import org.programming.pet.offerua.question.QuestionWithAnswersDto;
import org.programming.pet.offerua.question.QuestionsExternalApi;
import org.programming.pet.offerua.question.exception.QuestionErrorCodes;
import org.programming.pet.offerua.question.exception.QuestionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionFacade implements QuestionsExternalApi {
    private final QuestionService questionService;
    private final AnswersInternalApi answersInternalApi;

    @Override
    public PageResponse<QuestionDto> findAllQuestionRelatedToLanguage(UUID topicId, QuestionFilter questionFilterRequest) {
        var pageable = PageableUtils.getPageable(questionFilterRequest);
        var questionDtoPage = questionService.findByTopicId(topicId, pageable);
        return new PageResponse<>(questionDtoPage);
    }

    @Override
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
}
