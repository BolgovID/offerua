package org.programming.pet.offerua.security.service.factory;

import org.programming.pet.offerua.security.model.UserDetailsImpl;
import org.programming.pet.offerua.users.UserDto;
import org.programming.pet.offerua.users.UserRoleDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsFactory {

    public UserDetails create(UserDto user) {
        var authorities = user.roles().stream()
                .map(this::createSimpleGrantedAuthority)
                .toList();

        return new UserDetailsImpl(user.username(), user.password(), authorities);
    }

    private SimpleGrantedAuthority createSimpleGrantedAuthority(UserRoleDto role) {
        return new SimpleGrantedAuthority(role.name().toUpperCase());
    }
}
