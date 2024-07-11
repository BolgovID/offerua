package org.programming.pet.offerua.vault.exception;

public class VerificationTokenNotExistException extends RuntimeException {

    public VerificationTokenNotExistException(String token) {
        super("Verification token " + token + " not exist");
    }
}