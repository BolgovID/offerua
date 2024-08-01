package org.programming.pet.offerua.technologies.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TechnologiesRepository extends JpaRepository<TechnologyEntity, UUID> {
    boolean existsByTechnologyName(String techName);

    boolean existsByTechnologyDisplayName(String techName);

    Optional<TechnologyEntity> findByTechnologyName(String tech);
}
