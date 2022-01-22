package com.gmail.williammingardi.forum.controller

import com.gmail.williammingardi.forum.dto.NewTopicForm
import com.gmail.williammingardi.forum.dto.TopicView
import com.gmail.williammingardi.forum.service.TopicService
import org.springframework.web.bind.annotation.*
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
        return service.getTopicView(id)
    }

    @PostMapping
    fun addTopic(@RequestBody @Valid newTopicForm: NewTopicForm) =
        service.addTopic(newTopicForm)
}