package org.programming.pet.offerua.answers.controller;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.answers.AnswersExternalApi;
import org.programming.pet.offerua.answers.QuestionWithAnswersDto;
import org.programming.pet.offerua.common.dto.PaginationRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswersExternalApi answersExternalApi;

    @GetMapping("/answers/questions/{questionId}")
    public QuestionWithAnswersDto getAllAnswers(@PathVariable UUID questionId, PaginationRequest paginationRequest) {
        return answersExternalApi.findAllAnswersByQuestionId(questionId, paginationRequest);
    }
}
