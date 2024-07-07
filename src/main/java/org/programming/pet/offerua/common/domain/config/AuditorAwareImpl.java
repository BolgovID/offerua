package org.programming.pet.offerua.common.domain.config;

import jakarta.annotation.Nonnull;
import org.programming.pet.offerua.users.domain.UserEntity;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Nonnull
    @Override
    public Optional<String> getCurrentAuditor() {
        var user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return Optional.ofNullable(user)
                .map(UserEntity::getUsername);
    }
}
