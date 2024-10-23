package org.programming.pet.offerua.gateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.technologies.TechnologiesExternalApi;
import org.programming.pet.offerua.technologies.TechnologyDto;
import org.programming.pet.offerua.technologies.TechnologyUpdateRequest;
import org.programming.pet.offerua.technologies.TechnologyWithQuestionCountDto;
import org.programming.pet.offerua.topic.TopicDto;
import org.programming.pet.offerua.topic.TopicExternalApi;
import org.programming.pet.offerua.topic.UpdateTopicRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/technologies")
@RequiredArgsConstructor
@Slf4j
public class TechnologyController {
    private final TechnologiesExternalApi technologiesExternalApi;
    private final TopicExternalApi topicExternalApi;

    @PostMapping
    public TechnologyDto createTechnology(@RequestBody TechnologyUpdateRequest technologyUpdateRequest) {
        return technologiesExternalApi.createTechnology(technologyUpdateRequest);
    }

    @GetMapping
    public List<TechnologyWithQuestionCountDto> getTechnologies() {
        return technologiesExternalApi.getAllTechnologies();
    }

    @DeleteMapping("/{techId}")
    @ResponseStatus(HttpStatus.OK)
    public TechnologyDto deleteTechnology(@PathVariable UUID techId) {
        return technologiesExternalApi.deleteTechnologies(techId);
    }

    @PostMapping("/{techId}/topics")
    public TopicDto createTopic(@PathVariable UUID techId, @RequestBody UpdateTopicRequest updateTopicRequest) {
        return topicExternalApi.createNewTopic(techId, updateTopicRequest);
    }
}