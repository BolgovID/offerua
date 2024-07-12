package org.programming.pet.offerua.common.validator.handler;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.validator.PasswordsMatches;
import org.programming.pet.offerua.users.UserRegisterForm;

import java.util.Optional;

@Slf4j
public class PasswordMatchesValidator implements ConstraintValidator<PasswordsMatches, Object> {

    @Override
    public void initialize(PasswordsMatches constraintAnnotation) {
        // default implementation ignored
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        log.info("Validating passwords matches...");
        var user = (UserRegisterForm) obj;
        return Optional.ofNullable(user.password())
                .filter(password -> password.equals(user.confirmPassword()))
                .isPresent();
    }
}