package org.programming.pet.offerua.users;

public interface UsersExternalApi {
    void requestToRegister(String frontEndUrl, UserRegisterForm userDto);

    UserDto confirmRegistration(String data);

    void requestToResetPassword(String origin, String email);

    UserDto confirmReset(UserResetPasswordForm resetPasswordDto);
}
