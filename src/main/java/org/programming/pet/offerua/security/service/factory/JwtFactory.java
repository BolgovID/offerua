package org.programming.pet.offerua.security.service.factory;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.util.TimeUtils;
import org.programming.pet.offerua.common.config.properties.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.Key;
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
                .setIssuedAt(TimeUtils.currentDate())
                .setExpiration(obtainExpirationDate())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private Date obtainExpirationDate() {
        var expirationTime = TimeUtils.computeTimeAfterDuration(jwtProperties.expiresIn());
        return Date.from(expirationTime);
    }
}