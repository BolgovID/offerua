package org.programming.pet.offerua.question;

import lombok.Data;
import org.programming.pet.offerua.common.dto.PaginationRequest;

@Data
public class QuestionFilter extends PaginationRequest {
    private String questionTopic;
}

