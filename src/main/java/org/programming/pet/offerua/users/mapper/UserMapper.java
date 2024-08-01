package org.programming.pet.offerua.users.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.programming.pet.offerua.users.UserAuthDto;
import org.programming.pet.offerua.users.UserDto;
import org.programming.pet.offerua.users.UserRegisterForm;
import org.programming.pet.offerua.users.persistence.UserEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserAuthDto toAuthDto(UserEntity user);

    UserEntity toEntity(UserDto linkDto);

    UserDto toDto(UserEntity savedUser);

    UserDto toDto(UserRegisterForm userRegisterForm);
}
