package com.gmail.williammingardi.forum.controller

import com.gmail.williammingardi.forum.dto.NewTopicForm
import com.gmail.williammingardi.forum.dto.TopicView
import com.gmail.williammingardi.forum.dto.UpdateTopicForm
import com.gmail.williammingardi.forum.service.TopicService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicController(private val service: TopicService) {

    @GetMapping
    fun getTopics(): List<TopicView> {
        return service.getTopics()
    }

    @GetMapping("/{id}")
    fun getTopic(@PathVariable id: Long): TopicView {
        return service.getTopicViewById(id)
    }

    @PostMapping
    @Transactional
    fun addTopic(
        @RequestBody @Valid form: NewTopicForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicView> {
        val topicView = service.addTopic(form)
        val uri = uriBuilder.path("/topics/${topicView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicView)
    }

    @PutMapping
    @Transactional
    fun updateTopic(@RequestBody @Valid form: UpdateTopicForm): TopicView {
        return service.updateTopic(form)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deleteTopic(@PathVariable id: Long) {
        service.deleteTopic(id)
    }
}