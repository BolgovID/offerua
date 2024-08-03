package org.programming.pet.offerua.search.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.question.QuestionDto;
import org.programming.pet.offerua.search.domain.index.QuestionIndex;
import org.programming.pet.offerua.search.domain.mapper.QuestionIndexMapper;
import org.programming.pet.offerua.search.domain.service.QuestionIndexService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchQuestionCommandService {
    private final QuestionIndexService questionIndexService;
    private final QuestionIndexMapper questionIndexMapper;

    public QuestionIndex saveIndex(QuestionDto questionDto) {
        var questionIndex = questionIndexMapper.toQuestionIndex(questionDto);
        return questionIndexService.save(questionIndex);
    }

    public List<QuestionIndex> saveBulkIndex(List<QuestionDto> questions) {
        var questionsIndexes = questions.stream()
                .map(questionIndexMapper::toQuestionIndex)
                .toList();
        return questionIndexService.saveAll(questionsIndexes);
    }

}
