package org.programming.pet.offerua.question.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.question.QuestionUpdateRequest;
import org.programming.pet.offerua.question.mapper.QuestionMapper;
import org.programming.pet.offerua.question.service.domain.QuestionService;
import org.programming.pet.offerua.search.SearchInternalApi;
import org.programming.pet.offerua.topic.TopicInternalApi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class QuestionsCommandService {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final TopicInternalApi topicInternalApi;
    private final SearchInternalApi searchInternalApi;

    public QuestionDto createQuestion(QuestionUpdateRequest questionUpdateRequest) {
        log.info("Creating question: {}", questionUpdateRequest);
        var topic = topicInternalApi.findById(questionUpdateRequest.topicId());

        var questionEntity = questionMapper.toEntity(questionUpdateRequest);
        var savedQuestion = questionService.save(questionEntity);
        var questionDto = questionMapper.toDto(savedQuestion, topic.name());

        searchInternalApi.indexQuestion(questionDto);
        return questionDto;
    }
}
