package org.programming.pet.offerua.users.domain.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.dto.UserRoleName;
import org.programming.pet.offerua.users.domain.entity.UserRole;
import org.programming.pet.offerua.users.domain.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final RoleRepository roleRepository;

    public UserRole findByName(UserRoleName userRoleName) {
        return roleRepository.findByName(userRoleName)
                .orElseThrow(RuntimeException::new); //todo
    }
}
