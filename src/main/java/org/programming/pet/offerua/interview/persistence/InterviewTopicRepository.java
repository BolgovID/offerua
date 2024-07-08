package org.programming.pet.offerua.interview.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InterviewTopicRepository extends JpaRepository<InterviewTopicEntity, UUID> {
    boolean existsByTopicName(String languageName);
    boolean existsByTopicDisplayName(String languageName);
}
