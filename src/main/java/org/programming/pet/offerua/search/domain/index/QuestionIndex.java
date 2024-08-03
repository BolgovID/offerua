package org.programming.pet.offerua.search.domain.index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;
import org.programming.pet.offerua.common.dto.ESIndexes;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;
import java.util.UUID;

@Document(indexName = ESIndexes.QUESTION_INDEX)
@Setting(settingPath = "es-config/analyzers.json")
@JsonIgnoreProperties(ignoreUnknown = true)
public record QuestionIndex(
        @Id
        @UuidGenerator
        UUID id,
        @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
        String question,
        Double probability,
        String topic
) implements Serializable {
}
