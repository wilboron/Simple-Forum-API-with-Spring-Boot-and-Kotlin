package com.gmail.williammingardi.forum.repository;

import com.gmail.williammingardi.forum.model.Answer
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Id

interface AnswerRepository : JpaRepository<Answer, Long> {
    fun findByTopicId(topicId: Long): List<Answer>
}