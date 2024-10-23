package org.programming.pet.offerua.answers;

import org.programming.pet.offerua.common.dto.PageResponse;

import java.util.UUID;

public interface AnswersInternalApi {
    PageResponse<AnswerDto> findAnswersByQuestionId(UUID id, AnswerFilter paginationRequest);
}
