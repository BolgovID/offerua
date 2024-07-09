package org.programming.pet.offerua.topic.exception;

import java.util.UUID;

public class InterviewTopicNotExistException extends RuntimeException {


    public InterviewTopicNotExistException(UUID id) {
        super("Interview topic with id " + id + " does not exist");
    }
}
