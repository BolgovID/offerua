package org.programming.pet.offerua.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.security.service.factory.UserDetailsFactory;
import org.programming.pet.offerua.users.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserDetailsFactory userDetailsFactory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Entering in loadUserByUsername Method...");
        var user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("Username not found: {}", username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
        return userDetailsFactory.create(user);
    }
}
