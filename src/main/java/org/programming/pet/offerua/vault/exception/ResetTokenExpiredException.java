package org.programming.pet.offerua.vault.exception;


public class ResetTokenExpiredException extends RuntimeException {
    public ResetTokenExpiredException(String token) {
        super("Reset token expired: " + token);
    }
}
