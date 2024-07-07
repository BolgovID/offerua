package org.programming.pet.offerua.security.filter;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.util.AuthHeaderUtils;
import org.programming.pet.offerua.security.exception.WebFilterException;
import org.programming.pet.offerua.security.service.JwtService;
import org.programming.pet.offerua.security.service.UserDetailsServiceImpl;
import org.programming.pet.offerua.security.service.factory.AuthenticationTokenFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtWebFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final AuthenticationTokenFactory authenticationTokenFactory;

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain
    ) {
        AuthHeaderUtils.extractTokenFromRequest(request)
                .ifPresent(
                        token -> processToken(token, request)
                );
        proceedFilterChain(request, response, filterChain);
    }

    private void proceedFilterChain(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            filterChain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            throw new WebFilterException();
        }
    }

    private void processToken(String token, HttpServletRequest request) {
        var username = jwtService.extractUsername(token);
        if (Objects.nonNull(username) && !containInContext()) {
            var userDetails = userDetailsServiceImpl.loadUserByUsername(username);

            if (isValidToken(userDetails, token)) {
                authenticateUser(userDetails, request);
            }
        }
    }

    private void authenticateUser(UserDetails userDetails, HttpServletRequest request) {
        var authentication = authenticationTokenFactory.create(userDetails, request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private boolean isValidToken(UserDetails userDetails, String token) {
        return jwtService.validateToken(token, userDetails);
    }

    private boolean containInContext() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return Objects.nonNull(authentication);
    }
}
