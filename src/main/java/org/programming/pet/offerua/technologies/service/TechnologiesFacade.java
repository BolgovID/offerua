package org.programming.pet.offerua.technologies.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.PaginationRequest;
import org.programming.pet.offerua.technologies.*;
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
        return commandService.deleteTechnologies(id);
    }

    @Override
    public List<TechnologyWithQuestionCountDto> getAllTechnologies() {
        return queryService.getAllTechnologies();
    }

    @Override
    public TechnologyQuestionDto getAllTechnologyQuestions(String tech, PaginationRequest paginationRequest) {
        return queryService.getAllTechnologyQuestions(tech, paginationRequest);
    }

}