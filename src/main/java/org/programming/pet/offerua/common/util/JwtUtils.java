package org.programming.pet.offerua.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

@UtilityClass
public class JwtUtils {

    public Date calculateExpirationDate(Duration expireIn) {
        var expirationTime = TimeUtils.computeTimeAfterDuration(expireIn);
        return Date.from(expirationTime);
    }

    public String extractUsername(String token, String secret) {
        return extractClaim(token, secret, Claims::getSubject);
    }

    public Date extractExpiration(String token, String secret) {
        return extractClaim(token, secret, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, String secret, Function<Claims, T> claimsResolver) {
        var claims = extractAllClaims(token, secret);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token, String secret) {
        return Jwts.parserBuilder()
                .setSigningKey(EncryptionUtils.signKeyHmac(secret))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
