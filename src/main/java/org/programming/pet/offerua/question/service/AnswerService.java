package org.programming.pet.offerua.question.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.dto.PaginationRequest;
import org.programming.pet.offerua.common.util.PageableUtils;
import org.programming.pet.offerua.question.dto.AnswerByQuestionDto;
import org.programming.pet.offerua.question.mapper.AnswerMapper;
import org.programming.pet.offerua.question.mapper.QuestionMapper;
import org.programming.pet.offerua.question.repository.AnswerRepository;
import org.programming.pet.offerua.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final AnswerMapper answerMapper;
    private final QuestionMapper questionMapper;

    public AnswerByQuestionDto findAllAnswersByQuestionId(UUID id, PaginationRequest paginationRequest) {
        var pageable = PageableUtils.getPageable(paginationRequest);
        var question = questionRepository.findById(id)
                .map(questionMapper::toDto)
                .orElseThrow(RuntimeException::new);


        var answers = answerRepository.findByQuestionId(question.id(), pageable)
                .map(answerMapper::toDto);


        return new AnswerByQuestionDto(question, answers);

    }
}
