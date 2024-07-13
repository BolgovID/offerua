package org.programming.pet.offerua.common.validator.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.validator.PasswordsMatches;

import java.util.Map;
import java.util.Optional;

@Slf4j
public class PasswordMatchesValidator implements ConstraintValidator<PasswordsMatches, Object> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void initialize(PasswordsMatches constraintAnnotation) {
        // default implementation ignored
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        log.debug("Validating passwords matches...");
        var map = objectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {
        });
        var password = (String) map.get("password");
        var confirmPassword = (String) map.get("confirmPassword");
        return Optional.ofNullable(password)
                .filter(pass -> pass.equals(confirmPassword))
                .isPresent();
    }
}