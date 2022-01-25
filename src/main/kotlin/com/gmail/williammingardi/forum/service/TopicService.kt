package com.gmail.williammingardi.forum.service

import com.gmail.williammingardi.forum.dto.*
import com.gmail.williammingardi.forum.exception.NotFoundException
import com.gmail.williammingardi.forum.mapper.TopicFormMapper
import com.gmail.williammingardi.forum.mapper.TopicViewMapper
import com.gmail.williammingardi.forum.model.Topic
import com.gmail.williammingardi.forum.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TopicService(
    private var repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper
) {

    fun getTopics(
        courseName: String,
        authorName: String,
        pagination: Pageable
    ): Page<TopicView> {
        val topic = when {
            courseName.isEmpty() && authorName.isEmpty() -> repository.findAll(pagination)
            courseName.isNotEmpty() -> repository.findByCourseName(courseName, pagination)
            else -> repository.findByAuthorName(authorName, pagination)
        }
        return topic
            .map { topicViewMapper.map(it) }
    }

    fun getTopicViewById(id: Long): TopicView {
        return topicViewMapper.map(
            repository.findByIdOrNull(id)
                ?: throw throw NotFoundException("Topic", id)
        )
    }

    fun getTopicById(id: Long): Topic {
        return repository.findByIdOrNull(id)
            ?: throw NotFoundException("Topic", id)
    }

    fun addTopic(form: NewTopicForm): TopicView {
        val topic = topicFormMapper.map(form)
        repository.save(topic)
        return topicViewMapper.map(topic)
    }

    fun updateTopic(form: UpdateTopicForm): TopicView {
        val topic = repository.findByIdOrNull(form.id)
            ?: throw NotFoundException("Topic", form.id)
        topic.title = form.title
        topic.message = form.message
        repository.save(topic)
        return topicViewMapper.map(topic)
    }

    fun deleteTopic(id: Long) {
        if (!repository.existsById(id)) throw NotFoundException("Topic", id)

        repository.deleteById(id)
    }

    fun report(): List<TopicByCategoryDto> {
        return repository.report()
    }

    fun noAnswerReport(): List<Topic> {
        return repository.noAnswerReport()
    }
}