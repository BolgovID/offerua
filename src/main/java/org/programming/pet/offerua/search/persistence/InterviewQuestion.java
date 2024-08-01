package org.programming.pet.offerua.search.persistence;

import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.UUID;

@Document(indexName = "question_index")
public record InterviewQuestion(
        @Id
        @UuidGenerator
        UUID id,
        String question
) implements Serializable {
}
