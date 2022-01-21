package com.gmail.williammingardi.forum.controller

import com.gmail.williammingardi.forum.service.AnswerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("topics/{id}/answers")
class AnswerController(private val service: AnswerService) {

    @GetMapping
    fun getTopicAnswers(@PathVariable id: Long) = service.getTopicAnswers(id)
}