package org.programming.pet.offerua.search.application.query.builders;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.util.ObjectBuilder;
import lombok.experimental.UtilityClass;
import org.programming.pet.offerua.search.SearchQuestionRequest;
import org.programming.pet.offerua.search.application.util.ESQuestionConstants;

@UtilityClass
public class QuestionFiltersBuilders {

    public ObjectBuilder<BoolQuery> filterByTopicAndTechnologyNames(BoolQuery.Builder boolQuery, SearchQuestionRequest searchQuestionRequest) {
        searchQuestionRequest.filters()
                .forEach((key, value) -> boolQuery.filter(f -> f.terms(fq -> fq.field(key).terms(t -> t.value(value)))));
        return boolQuery;
    }

    public ObjectBuilder<BoolQuery> filterByTechnologyName(BoolQuery.Builder boolQuery, SearchQuestionRequest searchQuestionRequest) {
        return boolQuery.filter(f -> f.terms(fq -> fq
                .field(ESQuestionConstants.TECHNOLOGY_NAME_KEYWORD)
                .terms(t -> t.value(searchQuestionRequest.getTechnologyNameFilter()))));
    }
}
