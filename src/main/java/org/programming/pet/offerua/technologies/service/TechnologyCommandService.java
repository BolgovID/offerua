package org.programming.pet.offerua.technologies.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.technologies.TechnologyDto;
import org.programming.pet.offerua.technologies.TechnologyUpdateRequest;
import org.programming.pet.offerua.technologies.exception.TechErrorCodes;
import org.programming.pet.offerua.technologies.exception.TechnologyExistException;
import org.programming.pet.offerua.technologies.mapper.TechnologyMapper;
import org.programming.pet.offerua.technologies.service.domain.TechnologyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TechnologyCommandService {
    private final TechnologyService technologyService;
    private final TechnologyMapper technologyMapper;

    public TechnologyDto createTechnology(TechnologyUpdateRequest createTechRequest) {
        if (technologyService.existsByTechName(createTechRequest.technologyDisplayName())) {
            throw new TechnologyExistException(TechErrorCodes.TECH_NOT_UNIQUE, createTechRequest.technologyName());
        }
        if (technologyService.existsByTechDisplayName(createTechRequest.technologyDisplayName())) {
            throw new TechnologyExistException(TechErrorCodes.TECH_DISPLAY_NAME_NOT_UNIQUE, createTechRequest.technologyDisplayName());
        }
        var techEntity = technologyMapper.toEntity(createTechRequest);
        var savedTech = technologyService.save(techEntity);

        return technologyMapper.toDto(savedTech);
    }

    public TechnologyDto deleteTechnologies(UUID id) {
        var technology = technologyService.findById(id);
        technologyService.delete(technology.getId());
        return technologyMapper.toDto(technology);
    }
}
