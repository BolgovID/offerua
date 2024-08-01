package org.programming.pet.offerua.technologies;

import lombok.Builder;
import org.programming.pet.offerua.common.dto.PageResponse;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.topic.TopicDto;

import java.io.Serializable;
import java.util.Collection;

@Builder
public record TechnologyQuestionDto(
        TechnologyDto technology,
        Collection<TopicDto> topics,
        PageResponse<QuestionDto> questions
) implements Serializable {
}
