package org.programming.pet.offerua.gateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.vault.VaultExternalApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/api/v1/tokens")
@Slf4j
@RequiredArgsConstructor
public class TokenController {
    private final VaultExternalApi vaultExternalApi;

    @GetMapping("/reset/validate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> validateResetToken(@RequestParam String token) {
        log.info("Received GET /api/v1/tokens/reset/validate");
        var validatedResetToken = vaultExternalApi.validateResetToken(token);
        return ResponseEntity.ok(validatedResetToken);
    }
}