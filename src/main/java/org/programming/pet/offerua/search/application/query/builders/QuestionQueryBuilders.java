package org.programming.pet.offerua.search.application.query.builders;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.util.ObjectBuilder;
import lombok.experimental.UtilityClass;
import org.programming.pet.offerua.search.SearchQuestionRequest;
import org.programming.pet.offerua.search.application.util.ESQuestionConstants;
import org.programming.pet.offerua.search.application.util.ESUtils;

@UtilityClass
public class QuestionQueryBuilders {

    public ObjectBuilder<BoolQuery> buildMatchAllQuery(BoolQuery.Builder boolQuery) {
        return boolQuery.minimumShouldMatch(ESUtils.NONE_MATCH)
                .must(QuestionQueryBuilders::matchAll);
    }

    public ObjectBuilder<BoolQuery> buildTermQuery(BoolQuery.Builder boolQuery, SearchQuestionRequest searchQuestionRequest) {
        return boolQuery.minimumShouldMatch(ESUtils.AT_LEAST_ONE_MATCH)
                .should(qb -> matchMinorFields(qb, searchQuestionRequest))
                .should(qb -> matchQuestionField(qb, searchQuestionRequest));
    }

    public ObjectBuilder<Query> matchQuestionField(Query.Builder qb, SearchQuestionRequest searchQuestionRequest) {
        return qb.match(mq -> mq.field(ESQuestionConstants.QUESTION)
                .fuzziness(ESUtils.FUZZINESS_AUTO)
                .query(searchQuestionRequest.text())
                .operator(Operator.And)
                .boost(10f));
    }

    public ObjectBuilder<Query> matchMinorFields(Query.Builder qb, SearchQuestionRequest searchQuestionRequest) {
        return qb.multiMatch(mm -> mm
                .query(searchQuestionRequest.text())
                .fuzziness(ESUtils.FUZZINESS_AUTO)
                .operator(Operator.And)
                .fields(ESQuestionConstants.TOPIC_NAME)
                .boost(5f));
    }

    public ObjectBuilder<Query> matchAll(Query.Builder qb) {
        return qb.matchAll(MatchAllQuery.of(self -> self));
    }

    public static ObjectBuilder<Query> suggest(Query.Builder builder, SearchQuestionRequest searchQuestionRequest) {
        return builder.match(MatchQuery.of(mq -> QuestionQueryBuilders.suggestQueryWithNgram(mq, searchQuestionRequest)));
    }

    private ObjectBuilder<MatchQuery> suggestQueryWithNgram(MatchQuery.Builder mb, SearchQuestionRequest searchQuestionRequest) {
        return mb.field(ESQuestionConstants.QUESTION_SUGGEST)
                .query(searchQuestionRequest.text())
                .analyzer(ESQuestionConstants.SUGGEST_ANALYZER);
    }
}
