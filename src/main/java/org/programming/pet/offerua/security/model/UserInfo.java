package org.programming.pet.offerua.security.model;

import java.io.Serializable;

public record UserInfo(
        String username
) implements Serializable {
}
