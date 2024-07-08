package org.programming.pet.offerua.answers;

import java.util.Date;
import java.util.UUID;

public record AnswerDto(
        UUID id,
        String answer,
        Integer rating,
        String createdBy,
        Date createdDate
) {
}