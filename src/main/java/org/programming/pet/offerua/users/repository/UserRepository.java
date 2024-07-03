package org.programming.pet.offerua.users.repository;

import org.programming.pet.offerua.users.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByUsername(String username);
}