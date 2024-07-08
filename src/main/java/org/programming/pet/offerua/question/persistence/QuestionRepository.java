package org.programming.pet.offerua.question.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {
    Page<QuestionEntity> findByInterviewTopicId(UUID interviewTopicId, Pageable pageable);
}
