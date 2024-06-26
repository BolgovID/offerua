package org.programming.pet.offerua.question.repository;

import org.programming.pet.offerua.question.domain.AnswerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, UUID> {
    Page<AnswerEntity> findByQuestionId(UUID questionId, Pageable pageable);

}
