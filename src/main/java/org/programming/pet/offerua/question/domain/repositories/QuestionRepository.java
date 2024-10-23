package org.programming.pet.offerua.question.domain.repositories;

import org.programming.pet.offerua.question.domain.entities.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {
    Page<QuestionEntity> findAllByTopicIdIn(Collection<UUID> topicIds, Pageable pageable);


    Long countByTopicIdIn(Collection<UUID> topicIds);
}
