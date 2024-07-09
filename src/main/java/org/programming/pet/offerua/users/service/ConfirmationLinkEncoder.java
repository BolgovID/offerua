package org.programming.pet.offerua.users.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.users.dto.UserConfirmationLinkDto;
import org.programming.pet.offerua.users.exception.LinkEncodingException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ConfirmationLinkEncoder {
    private final ObjectMapper objectMapper;

    public String encodeData(UserConfirmationLinkDto userDto) {
        try {
            var userAsBytes = objectMapper.writeValueAsBytes(userDto);
            return Encoders.BASE64.encode(userAsBytes);
        } catch (JsonProcessingException e) {
            throw new LinkEncodingException();
        }
    }

    public UserConfirmationLinkDto decodeFromLink(String encodedLink) {
        var decodedLink = Decoders.BASE64.decode(encodedLink);
        try {
            return objectMapper.readValue(decodedLink, UserConfirmationLinkDto.class);
        } catch (IOException e) {
            throw new LinkEncodingException();
        }
    }
}
