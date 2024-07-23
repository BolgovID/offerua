package org.programming.pet.offerua.question;

import java.io.Serializable;
import java.util.UUID;

public record QuestionDto(
        UUID id,
        String question,
        Double probability,
        String subTopic
) implements Serializable {
}
