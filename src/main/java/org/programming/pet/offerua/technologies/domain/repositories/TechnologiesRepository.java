package org.programming.pet.offerua.technologies.domain.repositories;

import org.programming.pet.offerua.technologies.domain.entity.TechnologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TechnologiesRepository extends JpaRepository<TechnologyEntity, UUID> {
    Optional<TechnologyEntity> findByTechnologyName(String tech);

    Optional<TechnologyEntity> findByTechnologyDisplayName(String displayName);
}
