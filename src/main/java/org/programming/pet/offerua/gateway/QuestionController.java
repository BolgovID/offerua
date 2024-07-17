package org.programming.pet.offerua.gateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.answers.AnswerFilter;
import org.programming.pet.offerua.common.dto.PageResponse;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.question.QuestionFilter;
import org.programming.pet.offerua.question.QuestionWithAnswersDto;
import org.programming.pet.offerua.question.QuestionsExternalApi;
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
    public PageResponse<QuestionDto> getAllQuestions(@PathVariable UUID topicId, QuestionFilter questionPaginationRequest) {
        return questionsExternalApi.findAllQuestionRelatedToLanguage(topicId, questionPaginationRequest);
    }

    @GetMapping("/{questionId}")
    public QuestionWithAnswersDto getQuestionWithAnswers(@PathVariable UUID questionId, AnswerFilter paginationRequest) {
        return questionsExternalApi.findAllAnswersByQuestionId(questionId, paginationRequest);
    }

}
