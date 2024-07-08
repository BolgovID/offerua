package org.programming.pet.offerua.question.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.question.QuestionsExternalApi;
import org.programming.pet.offerua.question.QuestionDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
    private final QuestionsExternalApi questionsExternalApi;

    @GetMapping("/topics/{topicId}")
    public Page<QuestionDto> getAllQuestions(@PathVariable UUID topicId, QuestionFilterRequest questionFilterRequest) {
        return questionsExternalApi.findAllQuestionRelatedToLanguage(topicId, questionFilterRequest);
    }

}
