package org.programming.pet.offerua.gateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.topic.InterviewTopicDto;
import org.programming.pet.offerua.topic.InterviewTopicExternalApi;
import org.programming.pet.offerua.topic.InterviewTopicUpdateRequest;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @CachePut(cacheNames = "interview-topic", key = "#result.id")
    public InterviewTopicDto createInterviewTopic(@RequestBody InterviewTopicUpdateRequest interviewTopic) {
        return interviewTopicExternalApi.createInterviewTopic(interviewTopic);
    }

    @GetMapping("/interview-topics")
    @Cacheable(cacheNames = "interview-topic")
    public List<InterviewTopicDto> getInterviewTopics() {
        return interviewTopicExternalApi.getAllInterviewTopics();
    }

    @DeleteMapping("/interview-topics/{id}")
    @CacheEvict(cacheNames = "interview-topic", key = "#id", beforeInvocation = true)
    public void deleteInterviewTopic(@PathVariable UUID id){
        interviewTopicExternalApi.deleteInterviewTopic(id);
    }
}
