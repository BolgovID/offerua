package org.programming.pet.offerua.users.persistence;

public enum UserRoleName {
    USER("USER"),
    ADMIN("ADMIN");

    private final String name;

    UserRoleName(String user) {
        this.name = user;
    }

    @Override
    public String toString() {
        return name;
    }
}
