package org.programming.pet.offerua.question.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.question.dto.QuestionDto;
import org.programming.pet.offerua.question.dto.QuestionFilterRequest;
import org.programming.pet.offerua.question.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/{id}/question")
    public Page<QuestionDto> getAllQuestions(@PathVariable UUID id, QuestionFilterRequest questionFilterRequest) {
        return questionService.findAllQuestionRelatedToLanguage(id, questionFilterRequest);
    }
}
