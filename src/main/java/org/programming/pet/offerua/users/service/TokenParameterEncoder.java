package org.programming.pet.offerua.users.service;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class TokenParameterEncoder {

    public String encodeData(String token) {
        return Encoders.BASE64.encode(token.getBytes(StandardCharsets.UTF_8));
    }

    public String decode(String encodedLink) {
        return new String(Decoders.BASE64.decode(encodedLink));
    }
}
