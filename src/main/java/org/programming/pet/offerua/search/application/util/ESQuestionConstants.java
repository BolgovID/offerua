package org.programming.pet.offerua.search.application.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ESQuestionConstants {
    //Indexes, Analyzers
    public static final String QUESTION_INDEX = "idx_question";
    public static final String SUGGEST_ANALYZER = "suggest_analyzer";
    public static final String SUGGEST_INDEX = "suggest_index";

    //Fields
    public static final String TECHNOLOGY_ID = "technology_id";
    public static final String TECHNOLOGY_NAME = "technology_name";
    public static final String QUESTION_SUGGEST = "question_suggest";
    public static final String QUESTION_ID = "question_id";
    public static final String QUESTION = "question";
    public static final String TOPIC_ID = "topic_id";
    public static final String TOPIC_NAME = "topic_name";

    //Keywords
    public static final String TOPIC_NAME_KEYWORD = "topic_name.keyword";
    public static final String TOPIC_ID_KEYWORD = "topic_id.keyword";
    public static final String TECHNOLOGY_NAME_KEYWORD = "technology_name.keyword";

    //Aggregations
    public static final String TOPIC_AGGREGATION = "topic_aggregation";
}
