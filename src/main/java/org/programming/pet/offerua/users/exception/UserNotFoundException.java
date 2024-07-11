package org.programming.pet.offerua.users.exception;

public abstract class UserNotFoundException extends RuntimeException{
    protected UserNotFoundException(String message) {
        super(message);
    }
}
