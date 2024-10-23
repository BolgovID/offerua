package org.programming.pet.offerua.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.security.service.factory.UserDetailsFactory;
import org.programming.pet.offerua.users.UsersInternalApi;
import org.programming.pet.offerua.users.domain.exception.UserNotExistException;
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
    private final UsersInternalApi usersInternalApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Entering in loadUserByUsername Method...");
        try {
            var userAuth = usersInternalApi.getUserAuthInfoByUsername(username);
            return userDetailsFactory.create(userAuth);
        } catch (UserNotExistException ex) {
            log.warn("Username not found: {}", username);
            throw new UsernameNotFoundException("could not found userInfo..!!");
        }
    }

    public List<String> getUserAuthorityNames(String username) {
        return loadUserByUsername(username)
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }
}