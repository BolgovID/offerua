package org.programming.pet.offerua.search.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;
import java.util.UUID;

import static org.programming.pet.offerua.search.application.util.ESQuestionConstants.QUESTION_ID;
import static org.programming.pet.offerua.search.application.util.ESQuestionConstants.QUESTION_INDEX;
import static org.programming.pet.offerua.search.application.util.ESQuestionConstants.QUESTION_SUGGEST;
import static org.programming.pet.offerua.search.application.util.ESQuestionConstants.SUGGEST_ANALYZER;
import static org.programming.pet.offerua.search.application.util.ESQuestionConstants.SUGGEST_INDEX;
import static org.programming.pet.offerua.search.application.util.ESQuestionConstants.TECHNOLOGY_ID;
import static org.programming.pet.offerua.search.application.util.ESQuestionConstants.TECHNOLOGY_NAME;
import static org.programming.pet.offerua.search.application.util.ESQuestionConstants.TOPIC_ID;
import static org.programming.pet.offerua.search.application.util.ESQuestionConstants.TOPIC_NAME;

@Document(indexName = QUESTION_INDEX)
@Setting(settingPath = "es-config/es-settings.json")
@JsonIgnoreProperties(ignoreUnknown = true)
public record QuestionIndex(
        @Id
        @Field
        UUID id,
        @Field(value = TECHNOLOGY_ID, type = FieldType.Text)
        UUID technologyId,
        @Field(value = TECHNOLOGY_NAME, type = FieldType.Keyword)
        String technologyName,
        @Field(value = TOPIC_ID, type = FieldType.Text)
        UUID topicId,
        @Field(value = TOPIC_NAME, type = FieldType.Keyword)
        String topicName,
        @Field(value = QUESTION_ID, type = FieldType.Keyword)
        UUID questionId,
        @Field(type = FieldType.Keyword)
        String question,
        @Field(value = QUESTION_SUGGEST,
                analyzer = SUGGEST_INDEX,
                searchAnalyzer = SUGGEST_ANALYZER)
        String questionSuggest
) implements Serializable {
}
