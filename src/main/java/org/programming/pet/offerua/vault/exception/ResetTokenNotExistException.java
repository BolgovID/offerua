package org.programming.pet.offerua.vault.exception;

public class ResetTokenNotExistException extends RuntimeException {
    public ResetTokenNotExistException(String token) {
        super("Reset token " + token + " does not exist");
    }
}
