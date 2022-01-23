package com.gmail.williammingardi.forum.repository;

import com.gmail.williammingardi.forum.model.Answer
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository : JpaRepository<Answer, Long> {
}