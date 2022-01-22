package com.gmail.williammingardi.forum.mapper

import com.gmail.williammingardi.forum.dto.NewTopicForm
import com.gmail.williammingardi.forum.model.Topic
import com.gmail.williammingardi.forum.service.CourseService
import com.gmail.williammingardi.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class TopicFormMapper(
    private val courseService: CourseService,
    private val userService: UserService
) : Mapper<NewTopicForm, Topic> {
    override fun map(t: NewTopicForm): Topic {
        return Topic(
            title = t.title,
            message = t.message,
            course = courseService.getCourse(t.idCourse),
            author = userService.getUser(t.idAuthor)
        )
    }
}
