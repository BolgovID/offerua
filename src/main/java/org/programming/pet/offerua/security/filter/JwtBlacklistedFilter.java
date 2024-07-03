package org.programming.pet.offerua.security.filter;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.util.AuthHeaderUtils;
import org.programming.pet.offerua.security.exception.WebFilterException;
import org.programming.pet.offerua.security.repositories.TokenBlacklist;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtBlacklistedFilter extends OncePerRequestFilter {
    private final TokenBlacklist tokenBlacklist;

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain
    ) {
        AuthHeaderUtils.extractTokenFromRequest(request)
                .ifPresentOrElse(
                        token -> handleToken(token, request, response, filterChain),
                        () -> proceedFilterChain(request, response, filterChain)
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

    private void handleToken(String token, HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        if (tokenBlacklist.isBlacklisted(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            proceedFilterChain(request, response, filterChain);
        }
    }
}
