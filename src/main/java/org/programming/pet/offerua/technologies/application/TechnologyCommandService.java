package org.programming.pet.offerua.technologies.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.technologies.TechnologyDto;
import org.programming.pet.offerua.technologies.TechnologyUpdateRequest;
import org.programming.pet.offerua.technologies.application.mapper.TechnologyMapper;
import org.programming.pet.offerua.technologies.domain.service.TechnologyService;
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
        technologyService.validateTechNameUniqueness(createTechRequest.technologyName());
        technologyService.validateTechDisplayNameUniqueness(createTechRequest.technologyDisplayName());

        var techEntity = technologyMapper.toEntity(createTechRequest);
        var savedTech = technologyService.save(techEntity);

        return technologyMapper.toDto(savedTech);
    }

    public TechnologyDto deleteTechnology(UUID id) {
        var technology = technologyService.findById(id);
        technologyService.delete(technology.getId());
        return technologyMapper.toDto(technology);
    }
}
