package org.programming.pet.offerua.answers;

import lombok.Builder;
import org.programming.pet.offerua.question.QuestionDto;
import org.springframework.data.domain.Page;

@Builder
public record QuestionWithAnswersDto(
        QuestionDto question,
        Page<AnswerDto> answers
) {
}
