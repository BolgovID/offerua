package org.programming.pet.offerua.interview.repository;

import org.programming.pet.offerua.interview.domain.InterviewTopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InterviewTopicRepository extends JpaRepository<InterviewTopicEntity, UUID> {
    boolean existsByTopicName(String languageName);
    boolean existsByTopicDisplayName(String languageName);
}
