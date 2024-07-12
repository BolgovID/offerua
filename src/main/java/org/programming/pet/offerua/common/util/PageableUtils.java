package org.programming.pet.offerua.common.util;

import lombok.experimental.UtilityClass;
import org.programming.pet.offerua.common.dto.PaginationRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@UtilityClass
public class PageableUtils {
    private static final String DESCENDING = "DESC";
    private static final String ID_FIELD = "id";
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 15;

    public Pageable getPageable(PaginationRequest<?> request) {
        var sortBy = request.getSortBy();
        var direction = request.getDirection();
        var pageNo = request.getPageNo();
        var pageSize = request.getPageSize();
        Sort.Direction sortDirection;

        if (pageNo == null || pageSize == null) {
            pageNo = DEFAULT_PAGE_NUMBER;
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if (request.getSortBy() == null || request.getSortBy().isBlank()) {
            sortBy = ID_FIELD;
        }
        if (request.getDirection() == null || request.getDirection().isBlank()) {
            direction = DESCENDING;
        }
        sortDirection = Sort.Direction.fromString(direction);
        return PageRequest.of(pageNo, pageSize, sortDirection, sortBy);
    }
}
