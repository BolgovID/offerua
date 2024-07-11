package org.programming.pet.offerua.users.exception;

public class UserEmailNotFoundException extends UserNotFoundException {
    public UserEmailNotFoundException(String email) {
        super("User with email " + email + " not found");
    }
}
