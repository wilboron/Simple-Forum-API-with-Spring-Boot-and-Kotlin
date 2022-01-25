package com.gmail.williammingardi.forum.service

import com.gmail.williammingardi.forum.dto.AnswerView
import com.gmail.williammingardi.forum.dto.NewAnswerForm
import com.gmail.williammingardi.forum.mapper.AnswerViewMapper
import com.gmail.williammingardi.forum.mapper.NewAnswerFormMapper
import com.gmail.williammingardi.forum.model.Answer
import com.gmail.williammingardi.forum.repository.AnswerRepository
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private val repository: AnswerRepository,
    private val newAnswerFormMapper: NewAnswerFormMapper,
    private val answerViewMapper: AnswerViewMapper
) {

    fun getTopicAnswers(topicId: Long): List<AnswerView> {
        return repository.findByTopicId(topicId).map {
            answerViewMapper.map(it)
        }
    }

    fun addTopicAnswer(topicId: Long, newAnswerForm: NewAnswerForm) {
        val answer = newAnswerFormMapper.map(newAnswerForm)
        repository.save(answer)
    }
}
