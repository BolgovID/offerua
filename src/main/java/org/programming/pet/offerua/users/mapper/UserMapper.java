package org.programming.pet.offerua.users.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.programming.pet.offerua.users.UserAuthDto;
import org.programming.pet.offerua.users.dto.UserConfirmationLinkDto;
import org.programming.pet.offerua.users.UserDto;
import org.programming.pet.offerua.users.UserRegisterDto;
import org.programming.pet.offerua.users.persistence.UserEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserAuthDto toAuthDto(UserEntity user);

    UserConfirmationLinkDto toLinkDto(UserRegisterDto userDto);

    UserEntity toEntity(UserConfirmationLinkDto linkDto);

    UserDto toDto(UserEntity savedUser);
}
