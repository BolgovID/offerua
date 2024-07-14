package org.programming.pet.offerua.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.security.service.factory.UserDetailsFactory;
import org.programming.pet.offerua.users.UsersInternalApi;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDetailsFactory userDetailsFactory;
    private final UsersInternalApi usersApiClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Entering in loadUserByUsername Method...");
        return usersApiClient.getUserAuthInfoByUsername(username)
                .map(userDetailsFactory::create)
                .orElseThrow(() -> {
                            log.warn("Username not found: {}", username);
                            return new UsernameNotFoundException("could not found userInfo..!!");
                        }
                );
    }

    public List<String> getUserAuthorityNames(String username) {
        return loadUserByUsername(username)
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }
}