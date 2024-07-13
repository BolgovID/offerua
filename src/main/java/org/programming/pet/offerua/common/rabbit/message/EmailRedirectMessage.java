package org.programming.pet.offerua.common.rabbit.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record EmailRedirectMessage(
        @JsonProperty(value = "sendTo") String sendTo,
        @JsonProperty(value = "firstName") String firstName,
        @JsonProperty(value = "redirectTo") String redirectTo
) implements Serializable {
}
