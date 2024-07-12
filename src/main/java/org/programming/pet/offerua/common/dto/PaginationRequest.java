package org.programming.pet.offerua.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaginationRequest<F> {
    Integer pageNo;
    Integer pageSize;
    String sortBy;
    String direction;
    F filter;
}

