package org.programming.pet.offerua.vault;

public interface VaultInternalApi {

    void pushVerificationToken(String refreshToken);

    String popVerificationToken(String token);

    String popRefreshToken(String refreshToken);

    void pushJwtToBlacklist(String jwt);

    boolean isJwtNotBlacklisted(String token);

    void pushResetToken(String resetToken);

    String popResetToken(String token);

    void pushRefreshToken(String refreshToken);
}
