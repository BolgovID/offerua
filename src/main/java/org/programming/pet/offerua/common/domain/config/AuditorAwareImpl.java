package org.programming.pet.offerua.common.domain.config;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Nonnull
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("BolgovID");
        // Can use Spring Security to return currently logged-in user
        // return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
    }
}
