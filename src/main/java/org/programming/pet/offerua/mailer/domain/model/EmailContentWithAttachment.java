package org.programming.pet.offerua.mailer.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class EmailContentWithAttachment extends AbstractEmailContent {
    private final EmailAttachment attachment;

    public EmailContentWithAttachment(String title, String message, EmailAttachment attachment) {
        super(title, message);
        this.attachment = attachment;
    }
}
