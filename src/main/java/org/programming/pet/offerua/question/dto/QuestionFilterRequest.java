package org.programming.pet.offerua.question.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.programming.pet.offerua.common.dto.PaginationRequest;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionFilterRequest extends PaginationRequest {
    String questionTopic;
}
