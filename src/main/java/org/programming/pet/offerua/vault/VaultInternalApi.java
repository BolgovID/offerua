package org.programming.pet.offerua.vault;

public interface VaultInternalApi {
    String generateVerificationToken(String username);

    String popUsernameByVerificationToken(String token);

    String popUsernameFromRefreshToken(String refreshToken);

    String generateRefreshToken(String username);

    void addJwtToBlacklist(String jwt);

    boolean isJwtNotBlacklisted(String token);

    String generateResetToken(String email);

    String popUserEmailByResetToken(String token);
}
