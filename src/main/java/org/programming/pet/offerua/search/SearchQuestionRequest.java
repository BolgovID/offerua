package org.programming.pet.offerua.search;

import co.elastic.clients.elasticsearch._types.FieldValue;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Range;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static org.programming.pet.offerua.search.application.util.ESQuestionConstants.TECHNOLOGY_NAME_KEYWORD;
import static org.programming.pet.offerua.search.application.util.ESQuestionConstants.TOPIC_NAME_KEYWORD;

@Builder
public record SearchQuestionRequest(
        @NotNull
        String text,

        @Range(min = 5, max = 20)
        int size,

        UUID searchAfterId,

        List<String> topicFilter,

        String technologyName
) {

    public List<Object> searchAfter() {
        return Objects.nonNull(searchAfterId) ? List.of(searchAfterId.toString()) : Collections.emptyList();
    }

    public List<FieldValue> getTechnologyNameFilter(){
        return List.of(FieldValue.of(technologyName));
    }

    public Map<String, List<FieldValue>> filters() {
        var filterMap = new HashMap<String, List<FieldValue>>();
        if (!CollectionUtils.isEmpty(topicFilter)) {
            filterMap.put(TOPIC_NAME_KEYWORD, topicFilter.stream().map(FieldValue::of).toList());
        }
        if (Objects.nonNull(technologyName)) {
            filterMap.put(TECHNOLOGY_NAME_KEYWORD, getTechnologyNameFilter());
        }
        return filterMap;
    }
}