package org.programming.pet.offerua.search;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record QuestionSearchResultDto(
        List<UUID> questionIds
) {
}
