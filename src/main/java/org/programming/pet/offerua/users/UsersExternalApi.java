package org.programming.pet.offerua.users;

public interface UsersExternalApi {
    void requestToRegister(UserRegisterDto userDto);

    UserDto confirmRegistration(String data);
}
