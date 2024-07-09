package org.programming.pet.offerua.security;

import lombok.Builder;

@Builder
public record JwtResponseDto(
        String accessToken,
        String refreshToken
) {
}
