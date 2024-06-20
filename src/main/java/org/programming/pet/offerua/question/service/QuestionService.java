package org.programming.pet.offerua.question.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.util.PageableUtils;
import org.programming.pet.offerua.question.dto.QuestionDto;
import org.programming.pet.offerua.question.dto.QuestionFilterRequest;
import org.programming.pet.offerua.question.mapper.QuestionMapper;
import org.programming.pet.offerua.question.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;

    public Page<QuestionDto> findAllQuestionRelatedToLanguage(UUID id, QuestionFilterRequest questionFilterRequest) {
        var pageable = PageableUtils.getPageable(questionFilterRequest);
        return questionRepository.findByInterviewTopicId(id, pageable)
                .map(questionMapper::toDto);
    }
}
