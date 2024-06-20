package org.programming.pet.offerua.common.util;

import lombok.experimental.UtilityClass;
import org.programming.pet.offerua.common.dto.PaginationRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@UtilityClass
public class PageableUtils {
    public static final String DESCENDING = "DESC";
    public static final String ID_FIELD = "id";

    public Pageable getPageable(PaginationRequest request) {
        String sortBy = request.getSortBy();
        String direction = request.getDirection();
        Sort.Direction sortDirection;
        if (request.getPageNo() == null || request.getPageSize() == null) {
            return null;
        }
        if (request.getSortBy() == null || request.getSortBy().isBlank()) {
            sortBy = ID_FIELD;
        }
        if (request.getDirection() == null || request.getDirection().isBlank()) {
            direction = DESCENDING;
        }
        sortDirection = Sort.Direction.fromString(direction);
        return PageRequest.of(request.getPageNo(), request.getPageSize(), sortDirection, sortBy);
    }
}
