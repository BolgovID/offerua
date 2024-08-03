package org.programming.pet.offerua.search.util;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

@UtilityClass
public class ElasticSearchUtil {
    public Supplier<Query> querySupplier() {
        return () -> Query.of(query -> query.matchAll(matchAllQuery()));
    }

    public Supplier<Query> querySupplierWithQuestionField(String question) {
        return () -> Query.of(query -> query.match(matchQueryWithQuestionField(question)));
    }

    public Supplier<Query> createSupplierAutosuggestion(String partialValue) {
        return () -> Query.of(query -> query.match(createAutoSuggestQuery(partialValue)));
    }

    private static MatchQuery matchQueryWithQuestionField(String fieldValue) {
        return new MatchQuery.Builder()
                .field("question")
                .query(fieldValue)
                .build();
    }

    private static MatchAllQuery matchAllQuery() {
        return new MatchAllQuery.Builder()
                .build();
    }


    private MatchQuery createAutoSuggestQuery(String partialValue) {
        return new MatchQuery.Builder()
                .field("question")
                .query(partialValue)
                .analyzer("autocomplete_search")
                .build();
    }

}