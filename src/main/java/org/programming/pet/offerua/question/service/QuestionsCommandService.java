package org.programming.pet.offerua.question.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.question.QuestionUpdateRequest;
import org.programming.pet.offerua.question.mapper.QuestionMapper;
import org.programming.pet.offerua.question.service.domain.QuestionService;
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

    public QuestionDto createQuestion(QuestionUpdateRequest questionDto) {
        log.info("Creating question: {}", questionDto);
        var topic = topicInternalApi.findById(questionDto.topicId());

        var questionEntity = questionMapper.toEntity(questionDto);
        var savedQuestion = questionService.save(questionEntity);
        return questionMapper.toDto(savedQuestion, topic.name());
    }
}
