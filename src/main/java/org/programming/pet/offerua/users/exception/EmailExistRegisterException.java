package org.programming.pet.offerua.users.exception;

public class EmailExistRegisterException extends RuntimeException {
    public EmailExistRegisterException(String email) {
        super("User with email " + email + " already exists");
    }
}
