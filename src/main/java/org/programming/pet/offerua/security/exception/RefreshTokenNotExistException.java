package org.programming.pet.offerua.security.exception;

public class RefreshTokenNotExistException extends RuntimeException {

    public RefreshTokenNotExistException() {
        super("Refresh token not exist");
    }
}
