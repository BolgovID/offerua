package org.programming.pet.offerua.technologies.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.exception.ErrorCodes;

@RequiredArgsConstructor
@Getter
public enum TechErrorCodes implements ErrorCodes {
    TECH_NOT_UNIQUE("TEC-TEC-001", "Technology name %s already exist"),
    TECH_DISPLAY_NAME_NOT_UNIQUE("TEC-TEC-002", "Technology display name %s already exist"),
    TECH_NOT_EXIST_BY_ID("TEC-TEC-003", "Technology with id %s not exist"),
    TECH_NOT_EXIST_BY_NAME("TEC-TEC-004", "Technology with name %s not exist");

    private final String code;
    private final String description;
}
