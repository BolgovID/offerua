package org.programming.pet.offerua.question.dto;

import org.springframework.data.domain.Page;

public record AnswerByQuestionDto(
        QuestionDto question,
        Page<AnswerDto> answers
) {
}
