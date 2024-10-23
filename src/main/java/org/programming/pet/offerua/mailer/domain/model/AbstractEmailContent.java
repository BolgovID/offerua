package org.programming.pet.offerua.mailer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class AbstractEmailContent {
    private final String title;
    private final String message;
}
