package org.programming.pet.offerua.users.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.programming.pet.offerua.users.UserRoleDto;
import org.programming.pet.offerua.users.domain.UserRole;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRoleMapper {
    UserRoleDto toDto(UserRole userRole);
}
