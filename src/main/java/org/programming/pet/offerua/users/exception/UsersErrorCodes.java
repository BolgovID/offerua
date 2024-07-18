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
    USERNAME_NOT_UNIQUE("USR-USR-004", "User with username %s already exist"),
    USER_EMAIL_NOT_CONFIRMED("USR-USR-005", "User with email %s not confirmed"),
    VERIFICATION_TOKEN_EXPIRED("USR-USR-006", "Verification token %s expired at %s"),
    RESET_TOKEN_EXPIRED("VLT-RES-007", "Reset token %s expired at %s");


    private final String code;
    private final String description;
}
