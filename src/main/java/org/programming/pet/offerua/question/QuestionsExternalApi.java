package org.programming.pet.offerua.question;

import org.programming.pet.offerua.answers.AnswerFilter;
import org.programming.pet.offerua.common.dto.PageResponse;

import java.util.UUID;

public interface QuestionsExternalApi {
    PageResponse<QuestionDto> findAllQuestionRelatedToLanguage(UUID id, QuestionFilter questionFilterRequest);

    QuestionWithAnswersDto findAllAnswersByQuestionId(UUID questionId, AnswerFilter paginationRequest);
}
