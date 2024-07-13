package org.programming.pet.offerua.question;

import lombok.Builder;
import org.programming.pet.offerua.answers.AnswerDto;
import org.programming.pet.offerua.common.dto.PageResponse;

@Builder
public record QuestionWithAnswersDto(
        QuestionDto question,
        PageResponse<AnswerDto> answers
) {
}
