package org.programming.pet.offerua.users.application.query;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.users.UserAuthDto;
import org.programming.pet.offerua.users.application.mapper.UserMapper;
import org.programming.pet.offerua.users.domain.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserAuthDto getUserAuthDtoByUsername(String username) {
        var user = userService.findByUsername(username);
        return userMapper.toAuthDto(user);
    }
}
