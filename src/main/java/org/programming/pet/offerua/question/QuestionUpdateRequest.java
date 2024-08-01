package org.programming.pet.offerua.question;

import java.util.UUID;

public record QuestionUpdateRequest(
        String question,
        UUID topicId
) {
}
