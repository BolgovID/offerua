package org.programming.pet.offerua.question.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.util.PageableUtils;
import org.programming.pet.offerua.question.QuestionsExternalApi;
import org.programming.pet.offerua.question.QuestionsInternalApi;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.question.controller.QuestionFilterRequest;
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

    @Override
    public Page<QuestionDto> findAllQuestionRelatedToLanguage(UUID id, QuestionFilterRequest questionFilterRequest) {
        var pageable = PageableUtils.getPageable(questionFilterRequest);
        return questionRepository.findByInterviewTopicId(id, pageable)
                .map(questionMapper::toDto);
    }

    @Override
    public Optional<QuestionDto> findById(UUID id) {
        return questionRepository.findById(id)
                .map(questionMapper::toDto);
    }
}
