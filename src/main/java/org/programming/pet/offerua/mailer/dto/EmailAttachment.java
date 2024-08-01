package org.programming.pet.offerua.mailer.dto;

import java.io.File;

public record EmailAttachment(
        File attachment,
        String description
) {
}
