package org.programming.pet.offerua.search.application.query.builders;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.CompositeAggregationSource;
import co.elastic.clients.util.ObjectBuilder;
import lombok.experimental.UtilityClass;
import org.programming.pet.offerua.search.application.util.ESQuestionConstants;

import java.util.List;
import java.util.Map;

@UtilityClass
public class QuestionAggregationBuilders {

    public static ObjectBuilder<Aggregation> topicFacets(Aggregation.Builder builder) {
        return builder.composite(t -> t.sources(QuestionAggregationBuilders.createTopicsAggregationTermsList()).size(10));
    }

    private List<Map<String, CompositeAggregationSource>> createTopicsAggregationTermsList() {
        var topicNameAgg = Map.of(
                ESQuestionConstants.TOPIC_NAME,
                CompositeAggregationSource.of(t -> t.terms(term -> term.field(ESQuestionConstants.TOPIC_NAME_KEYWORD)))
        );
        var topicIdAgg = Map.of(
                ESQuestionConstants.TOPIC_ID,
                CompositeAggregationSource.of(t -> t.terms(term -> term.field(ESQuestionConstants.TOPIC_ID_KEYWORD)))
        );
        return List.of(topicIdAgg, topicNameAgg);
    }
}
