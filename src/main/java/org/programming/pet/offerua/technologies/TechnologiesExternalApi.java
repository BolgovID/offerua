package org.programming.pet.offerua.technologies;

import org.programming.pet.offerua.common.dto.PaginationRequest;

import java.util.List;
import java.util.UUID;

public interface TechnologiesExternalApi {
    TechnologyDto createTechnology(TechnologyUpdateRequest topicCreateRequest);

    List<TechnologyWithQuestionCountDto> getAllTechnologies();

    TechnologyDto deleteTechnologies(UUID id);

    TechnologyQuestionDto getAllTechnologyQuestions(String tech, PaginationRequest paginationRequest);
}
