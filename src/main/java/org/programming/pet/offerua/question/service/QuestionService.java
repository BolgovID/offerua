package org.programming.pet.offerua.question.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.answers.AnswerFilter;
import org.programming.pet.offerua.answers.AnswersInternalApi;
import org.programming.pet.offerua.question.exception.QuestionErrorCodes;
import org.programming.pet.offerua.question.exception.QuestionNotFoundException;
import org.programming.pet.offerua.common.dto.PaginationRequest;
import org.programming.pet.offerua.common.util.PageableUtils;
import org.programming.pet.offerua.question.*;
import org.programming.pet.offerua.question.mapper.QuestionMapper;
import org.programming.pet.offerua.question.persistence.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService implements QuestionsInternalApi, QuestionsExternalApi {
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;
    private final AnswersInternalApi answersInternalApi;

    @Override
    public Page<QuestionDto> findAllQuestionRelatedToLanguage(UUID id, PaginationRequest<QuestionFilter> questionFilterRequest) {
        var pageable = PageableUtils.getPageable(questionFilterRequest);
        return questionRepository.findByInterviewTopicId(id, pageable)
                .map(questionMapper::toDto);
    }

    @Override
    public QuestionWithAnswersDto findAllAnswersByQuestionId(UUID questionId, PaginationRequest<AnswerFilter> paginationRequest) {
        var answers = answersInternalApi.findAllAnswersByQuestionId(questionId, paginationRequest);
        var question = questionRepository.findById(questionId)
                .map(questionMapper::toDto)
                .orElseThrow(() -> new QuestionNotFoundException(QuestionErrorCodes.QUESTION_NOT_FOUND, questionId));
        return QuestionWithAnswersDto.builder()
                .answers(answers)
                .question(question)
                .build();
    }

    @Override
    public Optional<QuestionDto> findById(UUID id) {
        return questionRepository.findById(id)
                .map(questionMapper::toDto);
    }
}
