package org.programming.pet.offerua.search.application.query;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.util.ObjectBuilder;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.search.SearchQuestionRequest;
import org.programming.pet.offerua.search.application.query.builders.QuestionAggregationBuilders;
import org.programming.pet.offerua.search.application.query.builders.QuestionFiltersBuilders;
import org.programming.pet.offerua.search.application.query.builders.QuestionQueryBuilders;
import org.programming.pet.offerua.search.application.util.ESQuestionConstants;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class QuestionQueryFactory {

    public NativeQuery createSuggestQuery(SearchQuestionRequest searchQuestionRequest) {
        return new NativeQueryBuilder()
                .withQuery(builder -> QuestionQueryBuilders.suggest(builder, searchQuestionRequest))
                .build();
    }

    public NativeQuery createSearchAllQuery(SearchQuestionRequest searchQuestionRequest) {
        return new NativeQueryBuilder()
                .withQuery(QuestionQueryBuilders::matchAll)
                .withSearchAfter(searchQuestionRequest.searchAfter())
                .withFilter(qb -> qb.bool(bq -> QuestionFiltersBuilders.filterByTechnologyName(bq, searchQuestionRequest)))
                .withAggregation(ESQuestionConstants.TOPIC_AGGREGATION, Aggregation.of(QuestionAggregationBuilders::topicFacets))
                .withSort(Sort.by(Sort.Direction.ASC, ESQuestionConstants.QUESTION_ID))
                .withMaxResults(searchQuestionRequest.size())
                .build();
    }

    public NativeQuery createSearchQuery(SearchQuestionRequest searchQuestionRequest) {
        return new NativeQueryBuilder()
                .withQuery(qb -> buildQuery(qb, searchQuestionRequest))
                .withSearchAfter(searchQuestionRequest.searchAfter())
                .withFilter(qb -> qb.bool(bq -> QuestionFiltersBuilders.filterByTopicAndTechnologyNames(bq, searchQuestionRequest)))
                .withSort(Sort.by(Sort.Direction.ASC, ESQuestionConstants.QUESTION_ID))
                .withAggregation(ESQuestionConstants.TOPIC_AGGREGATION, Aggregation.of(QuestionAggregationBuilders::topicFacets))
                .withMaxResults(searchQuestionRequest.size())
                .build();
    }

    private ObjectBuilder<Query> buildQuery(Query.Builder qb, SearchQuestionRequest searchQuestionRequest) {
        return StringUtils.hasText(searchQuestionRequest.text())
                ? qb.bool(boolQuery -> QuestionQueryBuilders.buildTermQuery(boolQuery, searchQuestionRequest))
                : qb.bool(QuestionQueryBuilders::buildMatchAllQuery);
    }
}
