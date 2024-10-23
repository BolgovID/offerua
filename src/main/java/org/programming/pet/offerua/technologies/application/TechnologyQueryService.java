package org.programming.pet.offerua.technologies.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.question.QuestionsInternalApi;
import org.programming.pet.offerua.technologies.TechnologyDto;
import org.programming.pet.offerua.technologies.TechnologyWithQuestionCountDto;
import org.programming.pet.offerua.technologies.application.mapper.TechnologyMapper;
import org.programming.pet.offerua.technologies.domain.service.TechnologyService;
import org.programming.pet.offerua.topic.TopicInternalApi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class TechnologyQueryService {
    private final TechnologyService technologyService;
    private final TechnologyMapper technologyMapper;
    private final TopicInternalApi topicInternalApi;
    private final QuestionsInternalApi questionsInternalApi;


    public List<TechnologyWithQuestionCountDto> getAllTechnologies() {
        var technologies = technologyService.findAllTechnologies();
        log.debug("Pulled technologies with topicFilter{}", technologies);
        var technologiesWithTopics = technologies.stream()
                .map(technologyMapper::toDto)
                .collect(Collectors.toMap(Function.identity(), this::getQuestionCountByTopic));

        return technologiesWithTopics.entrySet().stream()
                .map(techEntry -> technologyMapper.toDtoWithQuestionCount(techEntry.getKey(), techEntry.getValue()))
                .toList();
    }

    public Long getQuestionCountByTopic(TechnologyDto tech) {
        return topicInternalApi.findAllTopicsByTechId(tech.id()).stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        questionsInternalApi::countQuestionsByTopicList
                ));
    }
}