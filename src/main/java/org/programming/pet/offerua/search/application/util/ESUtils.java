package org.programming.pet.offerua.search.application.util;

import co.elastic.clients.elasticsearch._types.aggregations.CompositeBucket;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregations;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.elasticsearch.core.SearchHitSupport.unwrapSearchHits;

@UtilityClass
public class ESUtils {
    public static final String FUZZINESS_AUTO = "AUTO";
    public static final String NONE_MATCH = "0";
    public static final String AT_LEAST_ONE_MATCH = "1";

    public <T> Page<T> pageOf(SearchHits<T> hits, Pageable pageable) {
        var searchPage = SearchHitSupport.searchPageFor(hits, pageable);
        return (Page<T>) unwrapSearchHits(searchPage);
    }

    public <T> List<CompositeBucket> unwrapAggregation(SearchHits<T> searchHits, String aggregationName) {
        var aggregations = (ElasticsearchAggregations) searchHits.getAggregations();
        var aggregationsList = Objects.requireNonNull(aggregations)
                .aggregationsAsMap()
                .get(aggregationName);

        return aggregationsList.aggregation()
                .getAggregate()
                .composite()
                .buckets()
                .array();
    }
}