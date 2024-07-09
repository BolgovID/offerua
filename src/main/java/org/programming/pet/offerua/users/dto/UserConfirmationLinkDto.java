package org.programming.pet.offerua.users.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class UserConfirmationLinkDto {
    private String username;
    private String firstName;
    private String surname;
    private String password;
    private String email;
    private Instant expirationDate;
}
