package org.programming.pet.offerua.answers.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.exception.ErrorCodes;

@Getter
@RequiredArgsConstructor
public enum QuestionErrorCodes implements ErrorCodes {
    QUESTION_NOT_FOUND("QST-QST-001", "Question with id %s not found");
    private final String code;
    private final String description;
}
