package org.programming.pet.offerua.question.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.PaginationRequest;
import org.programming.pet.offerua.common.util.PageableUtils;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.question.mapper.QuestionMapper;
import org.programming.pet.offerua.question.service.domain.QuestionService;
import org.programming.pet.offerua.topic.TopicDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionsQueryService {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    public Long countQuestionsByTopicIds(List<TopicDto> topics) {
        return topics.stream()
                .map(TopicDto::id)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        questionService::countByTopicIds
                ));
    }

    public Page<QuestionDto> findAllQuestionByTopicList(List<TopicDto> topics, PaginationRequest paginationRequest) {
        var pageable = PageableUtils.getPageable(paginationRequest);
        var topicById = topics.stream()
                .collect(Collectors.toMap(
                        TopicDto::id,
                        Function.identity()
                ));

        return questionService.findAllByTopicIds(topicById.keySet(), pageable)
                .map(question -> questionMapper.toDto(question, topicById.get(question.getTopicId()).name()));
    }
}