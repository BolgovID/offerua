package org.programming.pet.offerua.technologies.service.domain;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.dto.CacheConstants;
import org.programming.pet.offerua.technologies.exception.TechErrorCodes;
import org.programming.pet.offerua.technologies.exception.TechnologyNotExistException;
import org.programming.pet.offerua.technologies.persistence.TechnologiesRepository;
import org.programming.pet.offerua.technologies.persistence.TechnologyEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = CacheConstants.TECHNOLOGIES)
public class TechnologyService {
    private final TechnologiesRepository technologiesRepository;

    public boolean existsByTechName(String techName) {
        return technologiesRepository.existsByTechnologyName(techName);
    }

    public boolean existsByTechDisplayName(String topicDisplayName) {
        return technologiesRepository.existsByTechnologyDisplayName(topicDisplayName);
    }

    //    @CacheEvict(key = "#result.id")
    public TechnologyEntity save(TechnologyEntity technologyEntity) {
        return technologiesRepository.save(technologyEntity);
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

    //    @CacheEvict(key = "#techId", beforeInvocation = true)
    public void delete(UUID techId) {
        technologiesRepository.deleteById(techId);
    }

    public TechnologyEntity findByName(String techName) {
        return technologiesRepository.findByTechnologyName(techName)
                .orElseThrow(() -> new TechnologyNotExistException(TechErrorCodes.TECH_NOT_EXIST_BY_NAME, techName));
    }
}
