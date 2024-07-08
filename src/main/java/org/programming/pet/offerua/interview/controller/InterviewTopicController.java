package org.programming.pet.offerua.interview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.interview.InterviewTopicDto;
import org.programming.pet.offerua.interview.InterviewTopicExternalApi;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/interview-topics")
@RequiredArgsConstructor
@Slf4j
public class InterviewTopicController {
    private final InterviewTopicExternalApi interviewTopicExternalApi;

    @PostMapping
    public InterviewTopicDto createInterviewTopic(@RequestBody InterviewTopicUpdateRequest topicCreateRequest) {
        return interviewTopicExternalApi.createInterviewTopic(topicCreateRequest);
    }

    @GetMapping
    public List<InterviewTopicDto> getInterviewTopics() {
        return interviewTopicExternalApi.getAllInterviewTopics();
    }

    @DeleteMapping("/{id}")
    public void deleteInterviewTopic(@PathVariable UUID id){
        interviewTopicExternalApi.deleteInterviewTopic(id);
    }
}
