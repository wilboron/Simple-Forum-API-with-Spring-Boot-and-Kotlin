package com.gmail.williammingardi.forum.service

import com.gmail.williammingardi.forum.dto.NewTopicForm
import com.gmail.williammingardi.forum.dto.TopicView
import com.gmail.williammingardi.forum.dto.UpdateTopicForm
import com.gmail.williammingardi.forum.exception.NotFoundException
import com.gmail.williammingardi.forum.mapper.TopicFormMapper
import com.gmail.williammingardi.forum.mapper.TopicViewMapper
import com.gmail.williammingardi.forum.model.Answer
import com.gmail.williammingardi.forum.model.Course
import com.gmail.williammingardi.forum.model.Topic
import com.gmail.williammingardi.forum.model.User
import org.springframework.stereotype.Service

@Service
class TopicService(
    private var topics: MutableList<Topic>,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper
) {
    init {
        val topic1 = Topic(
            id = 1, title = "Duvida Kotlin", message = "Variável no Kotlin", course = Course(
                id = 1, name = "Kotlin", category = "Programação"
            ), author = User(
                id = 1, name = "Ana Paula", email = "anapaula@gmail.com"
            )
        )
        val topic2 = Topic(
            id = 2, title = "Ajuda com Init", message = "Dúvidas no construtor", course = Course(
                id = 1, name = "Kotlin", category = "Programação"
            ), author = User(
                id = 1, name = "Ana Paula", email = "anapaula@gmail.com"
            )
        )
        val topic3 = Topic(
            id = 3,
            title = "Kotlin e funcional",
            message = "Como validar uma lambda",
            course = Course(
                id = 1, name = "Kotlin", category = "Programação"
            ),
            author = User(
                id = 1, name = "Ana Paula", email = "anapaula@gmail.com"
            ),
            answers = listOf(
                Answer(
                    id = 1, message = "Teste resposta", author = User(
                        id = 1, name = "Ana Paula", email = "anapaula@gmail.com"
                    ), topic = topic1, solution = true
                )
            )
        )

        topics = mutableListOf(topic1, topic2, topic3)
    }

    fun getTopics(): List<TopicView> {
        return topics.map {
            topicViewMapper.map(it)
        }
    }

    fun getTopicView(id: Long): TopicView {
        return topicViewMapper.map(topics.first { it.id == id })
    }

    fun getTopic(id: Long): Topic {
        return topics.first { it.id == id }
    }

    fun addTopic(form: NewTopicForm): TopicView {
        val topic = topicFormMapper.map(form).also { it.id = topics.size.inc().toLong() }
        topics.add(topic)
        return topicViewMapper.map(topic)
    }

    fun updateTopic(form: UpdateTopicForm): TopicView {
        val topic = topics.firstOrNull { it.id == form.id }?.also {
            topics.remove(it)
            topics.add(
                Topic(
                    id = form.id,
                    title = form.title,
                    message = form.message,
                    author = it.author,
                    course = it.course,
                    answers = it.answers,
                    status = it.status
                )
            )
        } ?: throw TopicNotFoundException(form.id)

        return topicViewMapper.map(topic)
    }

    fun deleteTopic(id: Long) {
        topics.firstOrNull { it.id == id }.also {
            topics.remove(it)
        } ?: throw TopicNotFoundException(id)
    }

    class TopicNotFoundException(val id: Long) : NotFoundException() {
        override val message = "Topic not found with id '$id'"
    }
}