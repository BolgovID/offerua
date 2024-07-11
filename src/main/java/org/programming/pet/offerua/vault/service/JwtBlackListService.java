package org.programming.pet.offerua.vault.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.config.properties.JwtProperties;
import org.programming.pet.offerua.vault.persistence.JwtBlackListRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(JwtProperties.class)
public class JwtBlackListService {
    private final JwtBlackListRepository blackListRepository;
    private final JwtProperties jwtProperties;

    public String addToBlacklist(String token) {
        return blackListRepository.save(token, jwtProperties.expiresIn());
    }

    public boolean isBlacklisted(String token) {
        return blackListRepository.contain(token);
    }

    public boolean isNotBlacklisted(String token) {
        return !isBlacklisted(token);
    }
}
