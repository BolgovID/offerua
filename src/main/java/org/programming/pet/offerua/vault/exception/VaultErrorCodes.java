package org.programming.pet.offerua.vault.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.exception.ErrorCodes;

@RequiredArgsConstructor
@Getter
public enum VaultErrorCodes implements ErrorCodes {
    REFRESH_TOKEN_EXPIRED("VLT-REF-001", "Refresh token %s expired at %s"),
    REFRESH_TOKEN_NOT_EXIST("VLT-REF-002", "Refresh token %s not exist"),
    RESET_TOKEN_EXPIRED("VLT-RES-001", "Reset token %s expired at %s"),
    RESET_TOKEN_NOT_EXIST("VLT-RES-002", "Reset token %s not exist"),
    VERIFICATION_TOKEN_EXPIRED("VLT-VER-001", "Verification token %s expired at %s"),
    VERIFICATION_TOKEN_NOT_EXIST("VLT-VER-002", "Verification token %s not exist");
    private final String code;
    private final String description;
}