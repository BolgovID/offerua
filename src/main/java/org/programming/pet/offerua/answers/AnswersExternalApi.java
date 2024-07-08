package org.programming.pet.offerua.answers;

import org.programming.pet.offerua.common.dto.PaginationRequest;

import java.util.UUID;

public interface AnswersExternalApi {
    QuestionWithAnswersDto findAllAnswersByQuestionId(UUID id, PaginationRequest paginationRequest);
}
