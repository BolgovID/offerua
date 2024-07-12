package org.programming.pet.offerua.common.validator.handler;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.programming.pet.offerua.common.validator.PasswordsMatches;
import org.programming.pet.offerua.users.UserRegisterForm;

import java.util.Optional;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordsMatches, Object> {

    @Override
    public void initialize(PasswordsMatches constraintAnnotation) {
        // default implementation ignored
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        var user = (UserRegisterForm) obj;
        return Optional.ofNullable(user.password())
                .filter(password -> password.equals(user.confirmPassword()))
                .isPresent();
    }
}