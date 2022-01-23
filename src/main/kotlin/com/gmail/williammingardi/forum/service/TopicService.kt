package com.gmail.williammingardi.forum.service

import com.gmail.williammingardi.forum.dto.NewTopicForm
import com.gmail.williammingardi.forum.dto.TopicView
import com.gmail.williammingardi.forum.dto.UpdateTopicForm
import com.gmail.williammingardi.forum.exception.NotFoundException
import com.gmail.williammingardi.forum.mapper.TopicFormMapper
import com.gmail.williammingardi.forum.mapper.TopicViewMapper
import com.gmail.williammingardi.forum.model.Topic
import com.gmail.williammingardi.forum.repository.TopicRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TopicService(
    private var repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper
) {

    fun getTopics(): List<TopicView> {
        return repository.findAll()
            .map { topicViewMapper.map(it) }
    }

    fun getTopicViewById(id: Long): TopicView {
        return topicViewMapper.map(
            repository.findByIdOrNull(id)
                ?: throw throw NotFoundException("Topic", id)
        )
    }

    fun getTopic(id: Long): Topic {
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
        repository.deleteById(id)
    }

}