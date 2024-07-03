package org.programming.pet.offerua.security.dto;

import lombok.Builder;

@Builder
public record JwtResponseDto(
        String accessToken,
        String refreshToken
) {
}
