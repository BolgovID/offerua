package org.programming.pet.offerua.mailer.dto;

import lombok.Getter;

@Getter
public class SimpleEmailContent extends AbstractEmailContent {
    public SimpleEmailContent(String title, String message) {
        super(title, message);
    }
}
