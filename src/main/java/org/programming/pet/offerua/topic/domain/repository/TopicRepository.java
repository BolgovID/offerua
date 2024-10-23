package org.programming.pet.offerua.topic.domain.repository;

import org.programming.pet.offerua.topic.domain.entities.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, UUID> {
    List<TopicEntity> findAllByTechnologyId(UUID techId);

    Optional<TopicEntity> findByName(String name);
}