package org.programming.pet.offerua.mailer.domain.model;

import java.io.File;

public record EmailAttachment(
        File attachment,
        String description
) {
}
