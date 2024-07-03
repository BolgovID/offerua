package org.programming.pet.offerua.security.service.factory;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.security.config.properties.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
@EnableConfigurationProperties(JwtProperties.class)
@RequiredArgsConstructor
public class JwtFactory {
    private final JwtProperties jwtProperties;

    public String createToken(Map<String, Object> claims, String username, Key key) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuer(jwtProperties.issuer())
                .setIssuedAt(obtainIssuedAt())
                .setExpiration(obtainExpirationDate())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private Date obtainExpirationDate() {
        var expirationTime = Instant.now().plus(jwtProperties.expiresIn());
        return Date.from(expirationTime);
    }

    private Date obtainIssuedAt() {
        return Date.from(Instant.now());
    }
}
