package org.programming.pet.offerua.users.domain.repository;

import org.programming.pet.offerua.common.dto.UserRoleName;
import org.programming.pet.offerua.users.domain.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, UUID> {
    Optional<UserRole> findByName(UserRoleName name);
}
