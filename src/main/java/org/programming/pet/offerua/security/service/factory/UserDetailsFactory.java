package org.programming.pet.offerua.security.service.factory;

import org.programming.pet.offerua.security.model.UserDetailsImpl;
import org.programming.pet.offerua.users.domain.UserEntity;
import org.programming.pet.offerua.users.domain.UserRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsFactory {

    public UserDetails create(UserEntity user) {
        var authorities = user.getRoles().stream()
                .map(this::createSimpleGrantedAuthority)
                .toList();

        return new UserDetailsImpl(user.getUsername(), user.getPassword(), authorities);
    }

    private SimpleGrantedAuthority createSimpleGrantedAuthority(UserRole role) {
        return new SimpleGrantedAuthority(role.getName().toUpperCase());
    }
}
