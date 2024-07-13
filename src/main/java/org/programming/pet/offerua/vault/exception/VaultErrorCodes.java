package org.programming.pet.offerua.vault.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.exception.ErrorCodes;

@RequiredArgsConstructor
@Getter
public enum VaultErrorCodes implements ErrorCodes {
    REFRESH_TOKEN_EXPIRED("VLT-REF-001", "Refresh redirectTo %s expired at %s"),
    REFRESH_TOKEN_NOT_EXIST("VLT-REF-002", "Refresh redirectTo %s not exist"),
    RESET_TOKEN_EXPIRED("VLT-RES-001", "Reset redirectTo %s expired at %s"),
    RESET_TOKEN_NOT_EXIST("VLT-RES-002", "Reset redirectTo %s not exist"),
    VERIFICATION_TOKEN_EXPIRED("VLT-VER-001", "Verification redirectTo %s expired at %s"),
    VERIFICATION_TOKEN_NOT_EXIST("VLT-VER-002", "Verification redirectTo %s not exist");
    private final String code;
    private final String description;
}
