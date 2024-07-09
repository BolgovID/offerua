package org.programming.pet.offerua.topic.exception;

public class DBFieldsUniqueException extends RuntimeException {

    public DBFieldsUniqueException() {
        super("Interview topic already exists");
    }
}
