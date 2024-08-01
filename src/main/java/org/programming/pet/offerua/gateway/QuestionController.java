package org.programming.pet.offerua.gateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.question.QuestionUpdateRequest;
import org.programming.pet.offerua.question.QuestionsExternalApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
    private final QuestionsExternalApi questionsExternalApi;

    @PostMapping
    public QuestionDto addQuestion(@RequestBody QuestionUpdateRequest questionDto) {
        return questionsExternalApi.createQuestion(questionDto);
    }

}
