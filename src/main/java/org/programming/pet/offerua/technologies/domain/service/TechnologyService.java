package org.programming.pet.offerua.technologies.domain.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.dto.CacheConstants;
import org.programming.pet.offerua.technologies.domain.entity.TechnologyEntity;
import org.programming.pet.offerua.technologies.domain.exception.TechErrorCodes;
import org.programming.pet.offerua.technologies.domain.exception.TechnologyExistException;
import org.programming.pet.offerua.technologies.domain.exception.TechnologyNotExistException;
import org.programming.pet.offerua.technologies.domain.repositories.TechnologiesRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = CacheConstants.TECHNOLOGIES)
public class TechnologyService {
    private final TechnologiesRepository technologiesRepository;

    //    @CacheEvict(key = "#result.id")
    public TechnologyEntity save(TechnologyEntity technologyEntity) {
        return technologiesRepository.save(technologyEntity);
    }

    //    @CacheEvict(key = "#techId", beforeInvocation = true)
    public void delete(UUID techId) {
        technologiesRepository.deleteById(techId);
    }

    //    @Cacheable
    public List<TechnologyEntity> findAllTechnologies() {
        return technologiesRepository.findAll();
    }

    //    @Cacheable(key = "#techId")
    public TechnologyEntity findById(UUID techId) {
        return technologiesRepository.findById(techId)
                .orElseThrow(() -> new TechnologyNotExistException(TechErrorCodes.TECH_NOT_EXIST_BY_ID, techId));
    }

    public TechnologyEntity findByName(String techName) {
        return technologiesRepository.findByTechnologyName(techName)
                .orElseThrow(() -> new TechnologyNotExistException(TechErrorCodes.TECH_NOT_EXIST_BY_NAME, techName));
    }

    public void validateTechNameUniqueness(String technologyName) {
        technologiesRepository.findByTechnologyName(technologyName).ifPresent(tech -> {
            throw new TechnologyExistException(TechErrorCodes.TECH_NOT_UNIQUE, tech.getTechnologyName());
        });
    }

    public void validateTechDisplayNameUniqueness( String displayName) {
        technologiesRepository.findByTechnologyDisplayName(displayName).ifPresent(tech -> {
            throw new TechnologyExistException(TechErrorCodes.TECH_DISPLAY_NAME_NOT_UNIQUE, tech.getTechnologyDisplayName());
        });
    }

}
