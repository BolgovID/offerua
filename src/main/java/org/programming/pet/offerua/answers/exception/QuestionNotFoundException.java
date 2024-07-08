package org.programming.pet.offerua.answers.exception;

import java.util.UUID;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException(UUID id) {
        super("Question with id " + id + " not found");
    }
}
