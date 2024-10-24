package org.programming.pet.offerua.search;

import org.programming.pet.offerua.common.dto.PageResponse;
import org.programming.pet.offerua.question.QuestionDto;

import java.util.List;

public record QuestionSearchResponse(
        PageResponse<QuestionDto> questions,
        List<QuestionTopicFacet> topics
) {
}
