package org.programming.pet.offerua.question;

import java.util.Optional;
import java.util.UUID;

public interface QuestionsInternalApi {
    Optional<QuestionDto> findById(UUID id);
}
