package org.programming.pet.offerua.topic.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, UUID> {
    boolean existsByName(String name);

    List<TopicEntity> findAllByTechnologyId(UUID techId);
}
