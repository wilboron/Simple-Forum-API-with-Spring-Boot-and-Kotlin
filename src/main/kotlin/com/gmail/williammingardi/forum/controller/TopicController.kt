package com.gmail.williammingardi.forum.controller

import com.gmail.williammingardi.forum.service.TopicService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topics")
class TopicController(private val service: TopicService) {

    @GetMapping
    fun getTopics() = service.getTopics()

    @GetMapping("/{id}")
    fun getTopic(@PathVariable id: Long) = service.getTopic(id)

}