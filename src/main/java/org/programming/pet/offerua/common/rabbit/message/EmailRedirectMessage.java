package org.programming.pet.offerua.common.rabbit.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import org.programming.pet.offerua.common.validator.ValidEmail;

import java.io.Serializable;

@Builder
public record EmailRedirectMessage(
        @JsonProperty(value = "sendTo") @NotEmpty @ValidEmail String sendTo,
        @JsonProperty(value = "firstName") String firstName,
        @JsonProperty(value = "redirectTo") @NotEmpty String redirectTo
) implements Serializable {
}
