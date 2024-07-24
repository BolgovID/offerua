package org.programming.pet.offerua.security.filter;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.security.exception.JwtFilterException;
import org.programming.pet.offerua.security.service.AccessTokenService;
import org.programming.pet.offerua.security.service.UserDetailsServiceImpl;
import org.programming.pet.offerua.security.service.factory.AuthenticationTokenFactory;
import org.programming.pet.offerua.vault.VaultInternalApi;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final AccessTokenService accessTokenService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final AuthenticationTokenFactory authenticationTokenFactory;
    private final VaultInternalApi vaultInternalApi;

    public static final String ACCESS_TOKEN = "access_token";

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain
    ) {
        Optional.ofNullable(WebUtils.getCookie(request, ACCESS_TOKEN))
                .map(Cookie::getValue)
                .ifPresent(token -> processToken(token, request));
        proceedFilterChain(request, response, filterChain);
    }

    private void processToken(String token, HttpServletRequest request) {
        try {
            Optional.of(token)
                    .filter(vaultInternalApi::isJwtNotBlacklisted)
                    .map(accessTokenService::extractUsername)
                    .filter(username -> isNotAuthenticated())
                    .map(userDetailsServiceImpl::loadUserByUsername)
                    .filter(userDetails -> accessTokenService.validateToken(token, userDetails))
                    .ifPresent(user -> authenticateUser(user, request));
        } catch (Exception e) {
            logger.error("Error while processing access token" + token, e);
            throw new JwtFilterException(e);
        }
    }

    private void proceedFilterChain(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            filterChain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            logger.error("Error while processing proceed access filtering in " + this.getClass(), e);
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