package org.programming.pet.offerua.common.validator.handler;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.programming.pet.offerua.common.validator.ValidEmail;

import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        // default implementation ignored
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return validateEmail(email);
    }

    private boolean validateEmail(String email) {
        var pattern = Pattern.compile(EMAIL_PATTERN);
        var matcher = pattern.matcher(email);
        return matcher.matches();
    }
}