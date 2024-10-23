package org.programming.pet.offerua.technologies;

import java.util.List;
import java.util.UUID;

public interface TechnologiesExternalApi {
    TechnologyDto createTechnology(TechnologyUpdateRequest topicCreateRequest);

    List<TechnologyWithQuestionCountDto> getAllTechnologies();

    TechnologyDto deleteTechnologies(UUID id);
}
