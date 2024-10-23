package org.programming.pet.offerua.technologies.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.technologies.TechnologiesExternalApi;
import org.programming.pet.offerua.technologies.TechnologyDto;
import org.programming.pet.offerua.technologies.TechnologyUpdateRequest;
import org.programming.pet.offerua.technologies.TechnologyWithQuestionCountDto;
import org.programming.pet.offerua.technologies.application.TechnologyCommandService;
import org.programming.pet.offerua.technologies.application.TechnologyQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TechnologiesFacade implements TechnologiesExternalApi {
    private final TechnologyCommandService commandService;
    private final TechnologyQueryService queryService;

    @Override
    public TechnologyDto createTechnology(TechnologyUpdateRequest createTechRequest) {
        return commandService.createTechnology(createTechRequest);
    }

    @Override
    public TechnologyDto deleteTechnologies(UUID id) {
        return commandService.deleteTechnology(id);
    }

    @Override
    public List<TechnologyWithQuestionCountDto> getAllTechnologies() {
        return queryService.getAllTechnologies();
    }
}