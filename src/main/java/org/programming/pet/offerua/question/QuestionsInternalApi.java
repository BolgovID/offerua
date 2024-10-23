package org.programming.pet.offerua.question;

import org.programming.pet.offerua.topic.TopicDto;

import java.util.List;

public interface QuestionsInternalApi {
    Long countQuestionsByTopicList(List<TopicDto> topic);
}
