package com.gmail.williammingardi.forum.controller

import com.gmail.williammingardi.forum.dto.NewAnswerForm
import com.gmail.williammingardi.forum.service.AnswerService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("topics/{id}/answers")
class AnswerController(private val service: AnswerService) {

    @GetMapping
    fun getTopicAnswers(@PathVariable id: Long) = service.getTopicAnswers(id)

    @PostMapping
    fun createTopicAnswers(
        @PathVariable id: Long,
        @RequestBody @Valid newAnswerForm: NewAnswerForm
    ) = service.addTopicAnswer(id, newAnswerForm)
}