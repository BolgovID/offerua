package org.programming.pet.offerua.search.application;

import co.elastic.clients.elasticsearch._types.aggregations.CompositeBucket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.PageResponse;
import org.programming.pet.offerua.search.QuestionSearchResponse;
import org.programming.pet.offerua.search.QuestionTopicFacet;
import org.programming.pet.offerua.search.SearchQuestionRequest;
import org.programming.pet.offerua.search.application.mapper.QuestionIndexMapper;
import org.programming.pet.offerua.search.application.query.QuestionQueryFactory;
import org.programming.pet.offerua.search.application.util.ESQuestionConstants;
import org.programming.pet.offerua.search.application.util.ESUtils;
import org.programming.pet.offerua.search.domain.QuestionIndex;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchQuestionQueryService {
    private final QuestionIndexMapper questionIndexMapper;
    private final ElasticsearchOperations elasticsearchOperations;
    private final QuestionQueryFactory queryFactory;

    private static final IndexCoordinates QUESTION_INDEX = IndexCoordinates.of(ESQuestionConstants.QUESTION_INDEX);

    public QuestionSearchResponse findAll(SearchQuestionRequest searchQuestionRequest) {
        var searchAllQuery = queryFactory.createSearchAllQuery(searchQuestionRequest);
        var searchHits = elasticsearchOperations.search(searchAllQuery, QuestionIndex.class, QUESTION_INDEX);

        var questionsPage = ESUtils.pageOf(searchHits, searchAllQuery.getPageable())
                .map(questionIndexMapper::toQuestionDto);

        var topicDto = unwrapTopicFacets(searchHits);

        return new QuestionSearchResponse(new PageResponse<>(questionsPage), topicDto);
    }

    public QuestionSearchResponse search(SearchQuestionRequest searchQuestionRequest) {
        var searchQuery = queryFactory.createSearchQuery(searchQuestionRequest);
        var searchHits = elasticsearchOperations.search(searchQuery, QuestionIndex.class, QUESTION_INDEX);

        var questionsPage = ESUtils.pageOf(searchHits, searchQuery.getPageable())
                .map(questionIndexMapper::toQuestionDto);

        var topicDto = unwrapTopicFacets(searchHits);

        return new QuestionSearchResponse(new PageResponse<>(questionsPage), topicDto);
    }

    public List<String> autocomplete(SearchQuestionRequest searchQuestionRequest) {
        var autocompleteQuery = queryFactory.createSuggestQuery(searchQuestionRequest);
        var searchHits = elasticsearchOperations.search(autocompleteQuery, QuestionIndex.class, QUESTION_INDEX);
        return searchHits.stream()
                .map(SearchHit::getContent)
                .map(QuestionIndex::question)
                .toList();
    }

    public List<QuestionTopicFacet> findAllFacets(SearchQuestionRequest searchQuestionRequest) {
        var query = queryFactory.createSearchAllQuery(searchQuestionRequest);
        var searchHits = elasticsearchOperations.search(query, QuestionIndex.class, QUESTION_INDEX);

        return unwrapTopicFacets(searchHits);
    }

    private List<QuestionTopicFacet> unwrapTopicFacets(SearchHits<QuestionIndex> searchHits) {
        return ESUtils.unwrapAggregation(searchHits, ESQuestionConstants.TOPIC_AGGREGATION).stream()
                .map(this::mapBucketToTopicDto)
                .toList();
    }

    private QuestionTopicFacet mapBucketToTopicDto(CompositeBucket bucket) {
        return new QuestionTopicFacet(
                bucket.key().get(ESQuestionConstants.TOPIC_ID).stringValue(),
                bucket.key().get(ESQuestionConstants.TOPIC_NAME).stringValue(),
                bucket.docCount()
        );
    }
}
