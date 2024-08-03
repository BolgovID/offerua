package org.programming.pet.offerua.search.application;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.common.dto.PageResponse;
import org.programming.pet.offerua.search.domain.index.QuestionIndex;
import org.programming.pet.offerua.search.domain.service.QuestionIndexNativeQueryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchQuestionQueryService {
    private final QuestionIndexNativeQueryService nativeQueryService;


    public PageResponse<QuestionIndex> processSearch(String question) {
        var queryFilter = Query.of(s -> s.match(new MatchQuery.Builder()
                .field("question")
                .query(question)
                .fuzziness("auto")
                .build()));

        var searchQuery = new NativeQueryBuilder()
                .withFilter(queryFilter)
                .withPageable(PageRequest.of(0, 5))
                .build();

        var searchResults = nativeQueryService.search(searchQuery);
        return new PageResponse<>(searchResults, searchQuery.getPageable());
    }

    public List<String> fetchSuggestions(String query) {
        var queryFilter = Query.of(s -> s.match(new MatchQuery.Builder()
                .field("question")
                .query(query)
                .analyzer("autocomplete_search")
                .build()));

        var searchResponse = new NativeQueryBuilder()
                .withFilter(queryFilter)
                .withPageable(PageRequest.of(0, 5))
                .build();

        return nativeQueryService.search(searchResponse).stream()
                .map(hit -> hit.getContent().question())
                .toList();
    }

}
