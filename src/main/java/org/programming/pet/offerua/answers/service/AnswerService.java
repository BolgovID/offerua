package org.programming.pet.offerua.answers.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.answers.AnswerDto;
import org.programming.pet.offerua.answers.AnswerFilter;
import org.programming.pet.offerua.answers.AnswersInternalApi;
import org.programming.pet.offerua.answers.mapper.AnswerMapper;
import org.programming.pet.offerua.answers.persistence.AnswerRepository;
import org.programming.pet.offerua.common.dto.PaginationRequest;
import org.programming.pet.offerua.common.util.PageableUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnswerService implements AnswersInternalApi {
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    public Page<AnswerDto> findAllAnswersByQuestionId(UUID questionId, PaginationRequest<AnswerFilter> paginationRequest) {
        var pageable = PageableUtils.getPageable(paginationRequest);
        return answerRepository.findByQuestionId(questionId, pageable)
                .map(answerMapper::toDto);
    }
}
