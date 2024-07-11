package org.programming.pet.offerua.mailer.exception;

public class SendMessageException extends RuntimeException{
    public SendMessageException() {
        super("Error while sending email");
    }
}
