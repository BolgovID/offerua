package org.programming.pet.offerua.interview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.interview.dto.InterviewTopicDto;
import org.programming.pet.offerua.interview.dto.InterviewTopicUpdateRequest;
import org.programming.pet.offerua.interview.service.InterviewTopicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/interview-topic")
@RequiredArgsConstructor
@Slf4j
public class InterviewTopicController {
    private final InterviewTopicService interviewTopicService;

    @PostMapping
    public InterviewTopicDto createInterviewTopic(@RequestBody InterviewTopicUpdateRequest topicCreateRequest) {
        return interviewTopicService.createInterviewTopic(topicCreateRequest);
    }

    @GetMapping
    public List<InterviewTopicDto> getInterviewTopics() {
        return interviewTopicService.getAllInterviewTopics();
    }

    @DeleteMapping("/{id}")
    public void deleteInterviewTopic(@PathVariable UUID id){
        interviewTopicService.deleteInterviewTopic(id);
    }
}
