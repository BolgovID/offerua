package org.programming.pet.offerua.security.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.config.properties.AccessTokenProperties;
import org.programming.pet.offerua.common.util.JwtUtils;
import org.programming.pet.offerua.common.util.TimeUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@EnableConfigurationProperties(AccessTokenProperties.class)
@RequiredArgsConstructor
public class AccessTokenService {
    private final AccessTokenProperties accessTokenProperties;
    private final UserDetailsServiceImpl userDetailsService;

    public boolean validateToken(String token, UserDetails userDetails) {
        var username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token, accessTokenProperties.secret());
    }

    public String generateToken(String username) {
        var claims = new HashMap<String, Object>();

        var roles = userDetailsService.getUserAuthorityNames(username);
        claims.put("roles", roles);
        return JwtUtils.generateToken(username, claims, accessTokenProperties.issuer(), accessTokenProperties.expiresIn(), accessTokenProperties.secret());
    }

    public String extractUsername(String token) {
        return JwtUtils.extractSubject(token, accessTokenProperties.secret());
    }

    public boolean isTokenExpired(String token, String secret) {
        return JwtUtils.extractExpiration(token, secret)
                .before(TimeUtils.currentDate());
    }
}
