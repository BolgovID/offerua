package org.programming.pet.offerua.question.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.dto.PaginationRequest;
import org.programming.pet.offerua.common.util.PageableUtils;
import org.programming.pet.offerua.question.dto.AnswerDto;
import org.programming.pet.offerua.question.mapper.AnswerMapper;
import org.programming.pet.offerua.question.repository.AnswerRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    public Page<AnswerDto> findAllAnswersByQuestionId(UUID id, PaginationRequest paginationRequest) {
        var pageable = PageableUtils.getPageable(paginationRequest);
        return answerRepository.findByQuestionId(id, pageable)
                .map(answerMapper::toDto);
    }
}
