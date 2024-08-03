package org.programming.pet.offerua.search.domain.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.dto.ESIndexes;
import org.programming.pet.offerua.search.domain.index.QuestionIndex;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionIndexNativeQueryService {
    private final ElasticsearchOperations elasticsearchOperations;

    public SearchHits<QuestionIndex> search(Query searchQuery) {
        return elasticsearchOperations.search(
                searchQuery,
                QuestionIndex.class,
                IndexCoordinates.of(ESIndexes.QUESTION_INDEX)
        );
    }
}
