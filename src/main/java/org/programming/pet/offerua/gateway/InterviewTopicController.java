package org.programming.pet.offerua.gateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.topic.InterviewTopicDto;
import org.programming.pet.offerua.topic.InterviewTopicExternalApi;
import org.programming.pet.offerua.topic.InterviewTopicUpdateRequest;
import org.programming.pet.offerua.topic.TopicWithQuestionCountDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/interview-topics")
@RequiredArgsConstructor
@Slf4j
public class InterviewTopicController {
    private final InterviewTopicExternalApi interviewTopicExternalApi;

    @PostMapping
    public InterviewTopicDto createInterviewTopic(@RequestBody InterviewTopicUpdateRequest interviewTopic) {
        return interviewTopicExternalApi.createInterviewTopic(interviewTopic);
    }

    @GetMapping
    public List<TopicWithQuestionCountDto> getInterviewTopics() {
        return interviewTopicExternalApi.getAllInterviewTopics();
    }

    @DeleteMapping("/{topicId}")
    @ResponseStatus(HttpStatus.OK)
    public InterviewTopicDto deleteInterviewTopic(@PathVariable UUID topicId) {
        return interviewTopicExternalApi.deleteInterviewTopic(topicId);
    }
}