package org.programming.pet.offerua.common.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserRoleName {
    USER("USER"),
    ADMIN("ADMIN");
    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
