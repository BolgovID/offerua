package org.programming.pet.offerua.security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.config.properties.JwtProperties;
import org.programming.pet.offerua.common.util.EncryptionUtils;
import org.programming.pet.offerua.common.util.JwtUtils;
import org.programming.pet.offerua.common.util.TimeUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@EnableConfigurationProperties(JwtProperties.class)
@RequiredArgsConstructor
public class JwtService {
    private final JwtProperties jwtProperties;
    private final UserDetailsServiceImpl userDetailsService;

    public boolean validateToken(String token, UserDetails userDetails) {
        var username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token, jwtProperties.secret());
    }

    public String generateToken(String username) {
        var claims = new HashMap<String, Object>();

        var roles = userDetailsService.getUserAuthorityNames(username);
        claims.put("roles", roles);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuer(jwtProperties.issuer())
                .setIssuedAt(TimeUtils.currentDate())
                .setExpiration(JwtUtils.calculateExpirationDate(jwtProperties.expiresIn()))
                .signWith(EncryptionUtils.signKeyHmac(jwtProperties.secret()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return JwtUtils.extractUsername(token, jwtProperties.secret());
    }

    public Boolean isTokenExpired(String token, String secret) {
        return JwtUtils.extractExpiration(token, secret)
                .before(TimeUtils.currentDate());
    }
}
