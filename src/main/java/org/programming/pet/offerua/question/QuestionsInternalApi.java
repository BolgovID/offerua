package org.programming.pet.offerua.question;

import org.programming.pet.offerua.common.dto.PaginationRequest;
import org.programming.pet.offerua.topic.TopicDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuestionsInternalApi {
    Page<QuestionDto> findAllQuestionByTopicList(List<TopicDto> topics, PaginationRequest paginationRequest);

    Long countQuestionsByTopicList(List<TopicDto> topic);
}
