package com.gmail.williammingardi.forum.service

import com.gmail.williammingardi.forum.dto.NewAnswerForm
import com.gmail.williammingardi.forum.mapper.NewAnswerFormMapper
import com.gmail.williammingardi.forum.model.Answer
import com.gmail.williammingardi.forum.model.Course
import com.gmail.williammingardi.forum.model.Topic
import com.gmail.williammingardi.forum.model.User
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private var answers: MutableList<Answer>,
    private var topicService: TopicService,
    private var newAnswerFormMapper: NewAnswerFormMapper
) {
    init {
        val course = Course(
            id = 1,
            name = "Kotlin",
            category = "Programacao"
        )
        val author = User(
            id = 1,
            name = "Ana da Silva",
            email = "ana@email.com"
        )
        val topic = Topic(
            id = 1,
            title = "Duvida Kotlin",
            message = "Variaveis no Kotlin",
            course = course,
            author = author
        )

        val resposta = Answer(
            id = 1,
            message = "Resposta 1",
            author = author,
            topic = topic,
            solution = false
        )

        val resposta2 = Answer(
            id = 2,
            message = "Resposta 2",
            author = author,
            topic = topic,
            solution = false
        )

        answers = mutableListOf(resposta, resposta2)
    }

    fun getTopicAnswers(topicId: Long): List<Answer> {
        return answers.filter { it.topic.id == topicId }
            .ifEmpty { throw IllegalAccessError() }
    }

    fun addTopicAnswer(topicId: Long, newAnswerForm: NewAnswerForm) {
        answers.first { it.topic.id == newAnswerForm.topicId }
            .also {
                answers.add(
                    newAnswerFormMapper.map(
                        newAnswerForm.also { newAnswerForm ->
                            newAnswerForm.id = answers.size + 1L
                        })
                )
            }
    }
}
