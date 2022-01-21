package com.gmail.williammingardi.forum.service

import com.gmail.williammingardi.forum.model.Answer
import com.gmail.williammingardi.forum.model.Course
import com.gmail.williammingardi.forum.model.Topic
import com.gmail.williammingardi.forum.model.User
import org.springframework.stereotype.Service

@Service
class TopicService(private var topics: List<Topic>) {
    init {
        val topic1 = Topic(
            id = 1,
            title = "Duvida Kotlin",
            message = "Variável no Kotlin",
            course = Course(
                id = 1,
                name = "Kotlin",
                category = "Programação"
            ),
            author = User(
                id = 1,
                name = "Ana Paula",
                email = "anapaula@gmail.com"
            )
        )
        val topic2 = Topic(
            id = 2,
            title = "Ajuda com Init",
            message = "Dúvidas no construtor",
            course = Course(
                id = 1,
                name = "Kotlin",
                category = "Programação"
            ),
            author = User(
                id = 1,
                name = "Ana Paula",
                email = "anapaula@gmail.com"
            )
        )
        val topic3 = Topic(
            id = 3,
            title = "Kotlin e funcional",
            message = "Como validar uma lambda",
            course = Course(
                id = 1,
                name = "Kotlin",
                category = "Programação"
            ),
            author = User(
                id = 1,
                name = "Ana Paula",
                email = "anapaula@gmail.com"
            ),
            answers = listOf(
                Answer(
                    id = 1,
                    message = "Teste resposta",
                    author = User(
                        id = 1,
                        name = "Ana Paula",
                        email = "anapaula@gmail.com"
                    ),
                    topic = topic1,
                    solution = true
                )
            )
        )

        topics = listOf(topic1, topic2, topic3)
    }

    fun getTopics() = topics
    fun getTopic(id: Long): Topic {
        return topics.firstOrNull { it.id == id }
            ?: throw IllegalAccessError()
    }

}