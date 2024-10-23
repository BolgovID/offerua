package org.programming.pet.offerua.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResponse<T extends Serializable> implements Serializable {
    private int size;
    private long totalElements;
    private int totalPages;
    private List<T> content;
    private int currentPage;

    /**
     * Constructs a PageResponse from a Spring Data Page object.
     *
     * @param pageResult the Page object from Spring Data
     */
    public PageResponse(Page<T> pageResult) {
        this.setSize(pageResult.getSize());
        this.setTotalElements(pageResult.getTotalElements());
        this.setTotalPages(pageResult.getTotalPages());
        this.setCurrentPage(pageResult.getPageable().getPageNumber());
        this.setContent(pageResult.getContent());
    }

    /**
     * Constructs a PageResponse from SearchHits object.
     *
     * @param searchHits the SearchHits object from Elasticsearch
     * @param pageable   the Pageable object representing pagination info
     */
    public PageResponse(SearchHits<T> searchHits, Pageable pageable) {
        this.size = (int) searchHits.getTotalHits();
        this.totalElements = searchHits.getTotalHits();
        this.totalPages = calculateTotalPages(searchHits.getTotalHits(), pageable.getPageSize());
        this.currentPage = pageable.getPageNumber();
        this.content = searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .toList();
    }

    /**
     * Calculates the total number of pages.
     *
     * @param totalElements the total number of elements
     * @param pageSize      the number of elements per page
     * @return the total number of pages
     */
    private int calculateTotalPages(long totalElements, int pageSize) {
        if (pageSize <= 0) {
            return 0;
        }
        return (int) Math.ceil((double) totalElements / pageSize);
    }

}
