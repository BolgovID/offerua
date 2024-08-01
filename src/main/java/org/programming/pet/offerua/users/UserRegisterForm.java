package org.programming.pet.offerua.users;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.programming.pet.offerua.common.validator.PasswordsMatches;
import org.programming.pet.offerua.common.validator.ValidEmail;

@PasswordsMatches
public record UserRegisterForm(
        @NotNull
        @Length(min = 4, max = 20)
        String username,

        @NotNull
        @Length(min = 2, max = 10)
        String firstName,

        @NotNull
        @Length(min = 2, max = 10)
        String surname,

        @NotNull
        @Length(min = 8, max = 20)
        String password,

        @NotNull
        @Length(min = 8, max = 10)
        String confirmPassword,

        @ValidEmail
        @NotNull
        @NotEmpty
        String email
) {
}
