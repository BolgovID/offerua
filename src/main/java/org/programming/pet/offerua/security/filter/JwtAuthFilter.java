package org.programming.pet.offerua.security.filter;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.util.RequestUtils;
import org.programming.pet.offerua.security.exception.JwtFilterException;
import org.programming.pet.offerua.security.service.JwtService;
import org.programming.pet.offerua.security.service.UserDetailsServiceImpl;
import org.programming.pet.offerua.security.service.factory.AuthenticationTokenFactory;
import org.programming.pet.offerua.vault.VaultInternalApi;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final AuthenticationTokenFactory authenticationTokenFactory;
    private final VaultInternalApi vaultInternalApi;

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain
    ) {
        RequestUtils.extractTokenFromCookies(request)
                .ifPresent(token -> processToken(token, request));
        proceedFilterChain(request, response, filterChain);
    }

    private void processToken(String token, HttpServletRequest request) {
        try {
            Optional.of(token)
                    .filter(vaultInternalApi::isJwtNotBlacklisted)
                    .map(jwtService::extractUsername)
                    .filter(username -> isNotAuthenticated())
                    .map(userDetailsServiceImpl::loadUserByUsername)
                    .filter(userDetails -> jwtService.validateToken(token, userDetails))
                    .ifPresent(user -> authenticateUser(user, request));
        } catch (Exception e) {
            logger.error("Error while processing jwt token" + token, e);
            throw new JwtFilterException(e);
        }
    }

    private void proceedFilterChain(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            filterChain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            logger.error("Error while processing proceed jwt filtering in " + this.getClass(), e);
            throw new JwtFilterException(e);
        }
    }

    private void authenticateUser(UserDetails userDetails, HttpServletRequest request) {
        var authentication = authenticationTokenFactory.create(userDetails, request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private boolean isNotAuthenticated() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .isEmpty();
    }
}