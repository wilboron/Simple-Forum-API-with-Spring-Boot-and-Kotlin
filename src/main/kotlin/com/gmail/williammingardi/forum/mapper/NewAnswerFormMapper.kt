package com.gmail.williammingardi.forum.mapper

import com.gmail.williammingardi.forum.dto.NewAnswerForm
import com.gmail.williammingardi.forum.model.Answer
import com.gmail.williammingardi.forum.service.TopicService
import com.gmail.williammingardi.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class NewAnswerFormMapper(
    private val topicService: TopicService,
    private val userService: UserService
) : Mapper<NewAnswerForm, Answer> {
    override fun map(t: NewAnswerForm): Answer {
        return Answer(
            id = t.id,
            message = t.message,
            author = userService.getUserById(t.authorId),
            topic = topicService.getTopicById(t.topicId),
            solution = t.solution
        )
    }
}