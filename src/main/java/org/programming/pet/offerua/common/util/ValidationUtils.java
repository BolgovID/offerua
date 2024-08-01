package org.programming.pet.offerua.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class ValidationUtils {
    private static final String DEFAULT_VALIDATION_ERROR_MESSAGE = "Invalid input";

    public Map<String, String> extractAllValidationErrors(MethodArgumentNotValidException exception) {
        var fieldErrors = extractFieldValidationErrors(exception);
        var globalMessage = extractGlobalValidationErrors(exception);
        return Stream.concat(globalMessage.entrySet().stream(), fieldErrors.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, String> extractFieldValidationErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, ValidationUtils::getDefaultMessage));
    }

    public Map<String, String> extractGlobalValidationErrors(MethodArgumentNotValidException exception) {
        return exception.getGlobalErrors()
                .stream()
                .collect(Collectors.toMap(ObjectError::getObjectName, ValidationUtils::getDefaultMessage));
    }


    public String getDefaultMessage(FieldError fieldError) {
        return Optional.ofNullable(fieldError.getDefaultMessage())
                .orElse(DEFAULT_VALIDATION_ERROR_MESSAGE);
    }

    public String getDefaultMessage(ObjectError objectError) {
        return Optional.ofNullable(objectError.getDefaultMessage())
                .orElse(DEFAULT_VALIDATION_ERROR_MESSAGE);
    }
}