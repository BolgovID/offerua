package org.programming.pet.offerua.question;

import java.io.Serializable;

public record QuestionFilter(
        String questionTopic
)implements Serializable {}
