package org.programming.pet.offerua.gateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.question.QuestionUpdateRequest;
import org.programming.pet.offerua.question.QuestionsExternalApi;
import org.programming.pet.offerua.search.QuestionSearchResponse;
import org.programming.pet.offerua.search.QuestionTopicFacet;
import org.programming.pet.offerua.search.SearchInternalApi;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
    private final QuestionsExternalApi questionsExternalApi;
    private final SearchInternalApi searchInternalApi;

    @PostMapping
    public QuestionDto addQuestion(@RequestBody QuestionUpdateRequest questionDto) {
        return questionsExternalApi.createQuestion(questionDto);
    }

    @GetMapping
    public QuestionSearchResponse findAllQuestion(
            @RequestParam @Range(min = 5, max = 20) int size,
            @RequestParam(required = false, value = "search_after_id") UUID searchAfterId,
            @RequestParam(value = "technology_name") String technologyName
    ) {
        return questionsExternalApi.findAllQuestions(size, searchAfterId, technologyName);
    }

    @GetMapping("/search")
    public QuestionSearchResponse searchQuestion(
            @RequestParam String text,
            @RequestParam @Range(min = 5, max = 20) int size,
            @RequestParam(required = false, value = "search_after_id") UUID searchAfterId,
            @RequestParam(required = false, value = "topic_filter") List<String> topicFilter,
            @RequestParam(value = "technology_name") String technologyName
    ) {
        return questionsExternalApi.searchQuestions(text, size, searchAfterId, topicFilter, technologyName);
    }

    @GetMapping("/search/autocomplete")
    public List<String> searchAutocomplete(
            @RequestParam String text,
            @RequestParam @Range(min = 5, max = 20) int size,
            @RequestParam(value = "technology_name") String technologyName
    ) {
        return questionsExternalApi.searchQuestionsAutocomplete(text, size, technologyName);
    }

    @GetMapping("/topics")
    public List<QuestionTopicFacet> questionFacets(
            @RequestParam @Range(min = 5, max = 20) int size,
            @RequestParam(value = "technology_name") String technologyName
    ) {
        return questionsExternalApi.findQuestionTopic(size, technologyName);
    }
}