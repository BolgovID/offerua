package org.programming.pet.offerua.interview.exception;

public class DBFieldsUniqueException extends RuntimeException {

    public DBFieldsUniqueException() {
        super("Interview topic already exists");
    }
}
