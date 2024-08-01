package org.programming.pet.offerua.technologies;

import java.io.Serializable;
import java.util.UUID;

public record TechnologyDto(
        UUID id,
        String technologyName,
        String technologyDisplayName
) implements Serializable {
}
