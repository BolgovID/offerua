package org.programming.pet.offerua.users.exception;

public class LinkExpiredException extends RuntimeException{
    public LinkExpiredException() {
        super("Confirmation link expired");
    }
}
