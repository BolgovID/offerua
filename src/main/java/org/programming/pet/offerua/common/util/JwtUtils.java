package org.programming.pet.offerua.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@UtilityClass
@Slf4j
public class JwtUtils {

    public Date calculateExpirationDate(Duration expireIn) {
        var expirationTime = TimeUtils.computeTimeAfterDuration(expireIn);
        return Date.from(expirationTime);
    }

    public String generateToken(
            String subject,
            Map<String, Object> claims,
            String issuer,
            Duration expireIn,
            String secret
    ) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer(issuer)
                .setIssuedAt(TimeUtils.currentDate())
                .setExpiration(JwtUtils.calculateExpirationDate(expireIn))
                .signWith(EncryptionUtils.signKeyHmac(secret), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractSubject(String token, String secret) {
        return extractClaim(token, secret, Claims::getSubject);
    }

    public Date extractExpiration(String token, String secret) {
        return extractClaim(token, secret, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, String secret, Function<Claims, T> claimsResolver) {
        var claims = extractAllClaims(token, secret);
        return claimsResolver.apply(claims);
    }

    public static List<String> extractUserRole(String token, String secret) {
        return extractClaim(token, secret, claims -> claims.get("roles", List.class));
    }

    private Claims extractAllClaims(String token, String secret) {
        return Jwts.parserBuilder()
                .setSigningKey(EncryptionUtils.signKeyHmac(secret))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
