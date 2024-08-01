package org.programming.pet.offerua.security;

public record AuthRequest(
        String username,
        String password
) {
}
