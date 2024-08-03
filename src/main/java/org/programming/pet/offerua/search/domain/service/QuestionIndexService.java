package org.programming.pet.offerua.search.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.search.domain.index.QuestionIndex;
import org.programming.pet.offerua.search.infrastructure.persistence.QuestionElasticRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionIndexService {
    private final QuestionElasticRepository questionElasticRepository;

    public QuestionIndex save(QuestionIndex questionIndex) {
        return questionElasticRepository.save(questionIndex);
    }

    public List<QuestionIndex> saveAll(List<QuestionIndex> questionsIndexes) {
        return (List<QuestionIndex>) questionElasticRepository.saveAll(questionsIndexes);
    }

    public List<QuestionIndex> findByQuestionContaining(String partialQuestion) {
        return questionElasticRepository.findByQuestionContaining(partialQuestion);
    }
}
