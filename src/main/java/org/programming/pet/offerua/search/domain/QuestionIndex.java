package org.programming.pet.offerua.search.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.programming.pet.offerua.search.application.util.ESQuestionConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;
import java.util.UUID;

@Document(indexName = ESQuestionConstants.QUESTION_INDEX)
@Setting(settingPath = "es-config/es-settings.json")
@JsonIgnoreProperties(ignoreUnknown = true)
public record QuestionIndex(
        @Id
        @Field
        UUID id,
        @Field(value = ESQuestionConstants.TECHNOLOGY_ID, type = FieldType.Text)
        UUID technologyId,
        @Field(value = ESQuestionConstants.TECHNOLOGY_NAME, type = FieldType.Keyword)
        String technologyName,
        @Field(value = ESQuestionConstants.TOPIC_ID, type = FieldType.Text)
        UUID topicId,
        @Field(value = ESQuestionConstants.TOPIC_NAME, type = FieldType.Keyword)
        String topicName,
        @Field(value = ESQuestionConstants.QUESTION_ID, type = FieldType.Keyword)
        UUID questionId,
        @Field(type = FieldType.Keyword)
        String question,
        @Field(
                value = ESQuestionConstants.QUESTION_SUGGEST,
                analyzer = ESQuestionConstants.SUGGEST_INDEX,
                searchAnalyzer = ESQuestionConstants.SUGGEST_ANALYZER
        )
        String questionSuggest
) implements Serializable {
}
