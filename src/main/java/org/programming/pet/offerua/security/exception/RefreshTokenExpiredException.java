package org.programming.pet.offerua.security.exception;

public class RefreshTokenExpiredException extends RuntimeException {

    public RefreshTokenExpiredException(String token) {
        super("Refresh token " + token + " is expired. Please make a new login!");
    }
}
