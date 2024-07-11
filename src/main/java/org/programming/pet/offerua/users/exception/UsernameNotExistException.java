package org.programming.pet.offerua.users.exception;

public class UsernameNotExistException extends UserNotFoundException {
    public UsernameNotExistException(String username) {
        super("User with " + username + " does not exist");
    }
}
