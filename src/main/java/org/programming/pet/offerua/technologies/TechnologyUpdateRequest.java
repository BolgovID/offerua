package org.programming.pet.offerua.technologies;

import java.io.Serializable;

public record TechnologyUpdateRequest(
        String technologyName,
        String technologyDisplayName
) implements Serializable {
}
