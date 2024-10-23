package org.programming.pet.offerua.users.application.query;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.users.domain.service.VerificationTokenService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationQueryService {
    private final VerificationTokenService verificationTokenService;

    public String extractUsername(String token) {
        verificationTokenService.verifyExpiration(token);
        return verificationTokenService.extractUsername(token);
    }
}
