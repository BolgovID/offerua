package org.programming.pet.offerua.search.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.ESIndexes;
import org.programming.pet.offerua.search.domain.index.QuestionIndex;
import org.programming.pet.offerua.search.util.ElasticSearchUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
@Deprecated
public class ElasticSearchService {
    private final ElasticsearchClient elasticsearchClient;
    private final ElasticsearchOperations elasticsearchOperations;

    public SearchResponse<Map> matchAllQuery() throws IOException {
        var supplier = ElasticSearchUtil.querySupplier();
        log.debug("Elastic query is {}", supplier.get().toString());
        var searchResponse = elasticsearchClient.search(s -> s.query(supplier.get()), Map.class);
        log.debug("Search response: {}", searchResponse);
        return searchResponse;
    }

    public SearchResponse<QuestionIndex> matchQuestionFieldQuery(String question) throws IOException {
        var supplier = ElasticSearchUtil.querySupplierWithQuestionField(question);
        log.debug("Elastic query is {}", supplier.get().toString());
        var searchResponse = elasticsearchClient.search(s -> s.query(supplier.get()), QuestionIndex.class);
        log.debug("Search response: {}", searchResponse);
        return searchResponse;
    }

    public SearchPage<QuestionIndex> findAllWithAggregations(Pageable pageable) {
        Query query = NativeQuery.builder()
                .withAggregation("type_aggregation",
                        Aggregation.of(b -> b.terms(t -> t.field("type"))))
                .build();
        SearchHits<QuestionIndex> response = elasticsearchOperations.search(query, QuestionIndex.class);
        return SearchHitSupport.searchPageFor(response, pageable);
    }

    public SearchResponse<QuestionIndex> createAutosuggestion(String partialQuestion) throws IOException {
        var supplier = ElasticSearchUtil.createSupplierAutosuggestion(partialQuestion);
        log.debug("Elastic query is {}", supplier.get().toString());
        var searchResponse = elasticsearchClient
                .search(s -> s.index(ESIndexes.QUESTION_INDEX).query(supplier.get()), QuestionIndex.class);
        log.debug("Search response: {}", searchResponse);
        return searchResponse;
    }
}
