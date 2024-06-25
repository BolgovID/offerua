package org.programming.pet.offerua.question.dto;

import java.util.UUID;

public record QuestionDto(
        UUID id,
        String question,
        Double probability,
        String subTopic
) {
}
