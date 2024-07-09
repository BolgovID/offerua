package org.programming.pet.offerua.users.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.util.TimeUtils;
import org.programming.pet.offerua.users.*;
import org.programming.pet.offerua.users.config.ConfirmationLinkProperties;
import org.programming.pet.offerua.users.exception.EmailExistRegisterException;
import org.programming.pet.offerua.users.exception.LinkExpiredException;
import org.programming.pet.offerua.users.mapper.UserMapper;
import org.programming.pet.offerua.users.persistence.UserRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(ConfirmationLinkProperties.class)
public class UsersService implements UsersInternalApi, UsersExternalApi {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ConfirmationLinkEncoder linkEncoder;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationLinkProperties confirmationLinkProperties;

    @Override
    public Optional<UserAuthDto> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toAuthDto);
    }

    @Override
    public void requestToRegister(UserRegisterDto userDto) {
        var userOpt = userRepository.findByEmail(userDto.email());
        if (userOpt.isPresent()) {
            throw new EmailExistRegisterException(userOpt.get().getUsername());
        }
        var encodedPassword = passwordEncoder.encode(userDto.password());
        var expiredAt = TimeUtils.computeTimeAfterDuration(confirmationLinkProperties.expiresIn());

        var confirmationLinkDto = userMapper.toLinkDto(userDto);
        confirmationLinkDto.setPassword(encodedPassword);
        confirmationLinkDto.setExpirationDate(expiredAt);

        var dataToSend = linkEncoder.encodeData(confirmationLinkDto);
        //todo send event
    }

    @Override
    public UserDto confirmRegistration(String data) {
        var userConfirmationLinkDto = linkEncoder.decodeFromLink(data);
        if (userConfirmationLinkDto.getExpirationDate().isBefore(TimeUtils.currentTime())) {
            throw new LinkExpiredException();
        }
        var userEntity = userMapper.toEntity(userConfirmationLinkDto);
        var savedUser = userRepository.save(userEntity);
        return userMapper.toDto(savedUser);
    }
}
