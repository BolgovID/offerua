package org.programming.pet.offerua.answers.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.answers.AnswerDto;
import org.programming.pet.offerua.answers.AnswerFilter;
import org.programming.pet.offerua.answers.AnswersInternalApi;
import org.programming.pet.offerua.common.dto.PageResponse;
import org.programming.pet.offerua.common.util.PageableUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnswerFacade implements AnswersInternalApi {
    private final AnswerService answerService;

    @Override
    public PageResponse<AnswerDto> findAnswersByQuestionId(UUID questionId, AnswerFilter paginationRequest) {
        log.info("Collecting answers for question with id: {}", questionId);
        var pageable = PageableUtils.getPageable(paginationRequest);
        var answerDtoPage = answerService.findByQuestionId(questionId, pageable);
        return new PageResponse<>(answerDtoPage);
    }
}
