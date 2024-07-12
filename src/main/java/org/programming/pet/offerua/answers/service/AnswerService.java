package org.programming.pet.offerua.answers.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.answers.AnswersExternalApi;
import org.programming.pet.offerua.answers.QuestionWithAnswersDto;
import org.programming.pet.offerua.answers.AnswerFilter;
import org.programming.pet.offerua.answers.exception.QuestionErrorCodes;
import org.programming.pet.offerua.answers.exception.QuestionNotFoundException;
import org.programming.pet.offerua.answers.mapper.AnswerMapper;
import org.programming.pet.offerua.answers.persistence.AnswerRepository;
import org.programming.pet.offerua.common.dto.PaginationRequest;
import org.programming.pet.offerua.common.util.PageableUtils;
import org.programming.pet.offerua.question.QuestionsInternalApi;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnswerService implements AnswersExternalApi {
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final QuestionsInternalApi questionsInternalApi;

    public QuestionWithAnswersDto findAllAnswersByQuestionId(UUID questionId, PaginationRequest<AnswerFilter> paginationRequest) {
        var question = questionsInternalApi.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException(QuestionErrorCodes.QUESTION_NOT_FOUND, questionId));

        var pageable = PageableUtils.getPageable(paginationRequest);
        var answers = answerRepository.findByQuestionId(questionId, pageable)
                .map(answerMapper::toDto);

        return QuestionWithAnswersDto.builder()
                .question(question)
                .answers(answers)
                .build();
    }
}
