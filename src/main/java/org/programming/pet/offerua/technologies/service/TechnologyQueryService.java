package org.programming.pet.offerua.technologies.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.PageResponse;
import org.programming.pet.offerua.common.dto.PaginationRequest;
import org.programming.pet.offerua.question.QuestionsInternalApi;
import org.programming.pet.offerua.technologies.TechnologyDto;
import org.programming.pet.offerua.technologies.TechnologyQuestionDto;
import org.programming.pet.offerua.technologies.TechnologyWithQuestionCountDto;
import org.programming.pet.offerua.technologies.mapper.TechnologyMapper;
import org.programming.pet.offerua.technologies.service.domain.TechnologyService;
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
        log.debug("Pulls technologies with topics{}", technologies);
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

    public TechnologyQuestionDto getAllTechnologyQuestions(String techName, PaginationRequest paginationRequest) {
        var technologyEntity = technologyService.findByName(techName);
        log.debug("Found technology: {}", technologyEntity);

        var topics = topicInternalApi.findAllTopicsByTechId(technologyEntity.getId());

        var questions = questionsInternalApi.findAllQuestionByTopicList(topics, paginationRequest);

        var questionPageResponse = new PageResponse<>(questions);

        var technologyDto = technologyMapper.toDto(technologyEntity);

        return TechnologyQuestionDto.builder()
                .technology(technologyDto)
                .topics(topics)
                .questions(questionPageResponse)
                .build();
    }
}