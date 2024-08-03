package org.programming.pet.offerua.search.infrastructure.persistence;

import org.programming.pet.offerua.search.domain.index.QuestionIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionElasticRepository extends ElasticsearchRepository<QuestionIndex, UUID> {
    List<QuestionIndex> findByQuestion(String question);

    List<QuestionIndex> findByQuestionContaining(String partialQuestion);
}
