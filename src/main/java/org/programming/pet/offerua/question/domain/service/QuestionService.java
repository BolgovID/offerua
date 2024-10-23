package org.programming.pet.offerua.question.domain.service;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.question.domain.entities.QuestionEntity;
import org.programming.pet.offerua.question.domain.repositories.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Page<QuestionEntity> findAllByTopicIds(Collection<UUID> topicIds, Pageable pageable) {
        return questionRepository.findAllByTopicIdIn(topicIds, pageable);
    }

    public Long countByTopicIds(List<UUID> topicIds) {
        return questionRepository.countByTopicIdIn(topicIds);
    }

    public QuestionEntity save(QuestionEntity questionEntity) {
        return questionRepository.save(questionEntity);
    }
}
