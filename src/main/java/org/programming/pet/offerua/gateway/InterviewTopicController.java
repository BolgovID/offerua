package org.programming.pet.offerua.gateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.topic.InterviewTopicDto;
import org.programming.pet.offerua.topic.InterviewTopicExternalApi;
import org.programming.pet.offerua.topic.InterviewTopicUpdateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class InterviewTopicController {
    private final InterviewTopicExternalApi interviewTopicExternalApi;

    @PostMapping("/interview-topics")
    public InterviewTopicDto createInterviewTopic(@RequestBody InterviewTopicUpdateRequest interviewTopic) {
        return interviewTopicExternalApi.createInterviewTopic(interviewTopic);
    }

    @GetMapping("/interview-topics")
    public List<InterviewTopicDto> getInterviewTopics() {
        return interviewTopicExternalApi.getAllInterviewTopics();
    }

    @DeleteMapping("/interview-topics/{id}")
    public void deleteInterviewTopic(@PathVariable UUID id) {
        interviewTopicExternalApi.deleteInterviewTopic(id);
    }
}
