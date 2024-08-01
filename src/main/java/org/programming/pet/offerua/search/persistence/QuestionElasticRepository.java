package org.programming.pet.offerua.search.persistence;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionElasticRepository extends ElasticsearchRepository<InterviewQuestion, UUID> {
    List<InterviewQuestion> findByQuestion(String question);
}
