package org.programming.pet.offerua.users.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.exception.ErrorCodes;

@RequiredArgsConstructor
@Getter
public enum UsersErrorCodes implements ErrorCodes {
    USER_EMAIL_NOT_UNIQUE("USR-USR-001", "User email %s already exists"),
    USER_EMAIL_NOT_EXIST("USR-USR-002", "User with email %s not exists"),
    USERNAME_NOT_EXIST("USR-USR-003", "User with username %s not exists"),
    USERNAME_NOT_UNIQUE("USR-USR-004", "User with username %s already exist");

    private final String code;
    private final String description;
}
