package org.programming.pet.offerua.common.util;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;

import java.security.Key;

@UtilityClass
public class EncryptionUtils {

    public Key signKeyHmac(String secret) {
        var keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
