package org.programming.pet.offerua.security.dto;

public record AuthRequest(
        String username,
        String password
) {
}
