package org.programming.pet.offerua.common.dto;

import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaginationRequest {
    Integer pageNo;
    @Min(value = 1)
    Integer pageSize;
    String sortBy;
    String direction;
}

