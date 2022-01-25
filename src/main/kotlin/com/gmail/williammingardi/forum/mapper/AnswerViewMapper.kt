package com.gmail.williammingardi.forum.mapper

import com.gmail.williammingardi.forum.dto.AnswerView
import com.gmail.williammingardi.forum.model.Answer
import org.springframework.stereotype.Component

@Component
class AnswerViewMapper : Mapper<Answer, AnswerView> {
    override fun map(t: Answer): AnswerView {
        return AnswerView(
            id = t.id,
            message = t.message,
            authorId = t.author.id,
            topicId = t.topic.id,
            solution = t.solution,
            createdAt = t.createdAt
        )
    }
}