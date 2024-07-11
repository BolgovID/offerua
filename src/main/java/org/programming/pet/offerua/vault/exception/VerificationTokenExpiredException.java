package org.programming.pet.offerua.vault.exception;

public class VerificationTokenExpiredException extends RuntimeException {
    public VerificationTokenExpiredException(String token) {
        super("Verification token " + token + " is expired");
    }
}
