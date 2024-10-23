package org.programming.pet.offerua.users;

public interface UsersExternalApi {
    void requestToRegister(UserRegisterForm userDto);

    UserDto confirmRegistration(String data);

    void requestToResetPassword(String email);

    UserDto confirmReset(UserResetPasswordForm resetPasswordDto);
}
