package org.programming.pet.offerua.question;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.programming.pet.offerua.common.dto.PaginationRequest;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionFilter extends PaginationRequest {
    private String questionTopic;
}

