package org.programming.pet.offerua.question;

import org.programming.pet.offerua.common.dto.PaginationRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface QuestionsExternalApi {
    Page<QuestionDto> findAllQuestionRelatedToLanguage(UUID id, PaginationRequest<QuestionFilter> questionFilterRequest);
}
