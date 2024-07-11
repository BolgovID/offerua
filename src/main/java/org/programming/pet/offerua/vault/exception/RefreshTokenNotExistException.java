package org.programming.pet.offerua.vault.exception;

public class RefreshTokenNotExistException extends RuntimeException {

    public RefreshTokenNotExistException(String token) {
        super("Refresh token " + token + " not exist");
    }
}
