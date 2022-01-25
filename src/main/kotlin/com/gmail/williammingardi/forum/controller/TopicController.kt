package com.gmail.williammingardi.forum.controller

import com.gmail.williammingardi.forum.dto.*
import com.gmail.williammingardi.forum.model.Topic
import com.gmail.williammingardi.forum.service.TopicService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
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
    @Cacheable("topics")
    fun getTopics(
        @RequestParam(defaultValue = "") courseName: String,
        @RequestParam(defaultValue = "") authorName: String,
        @PageableDefault(sort = ["createdAt"], direction = Sort.Direction.DESC) pagination:
        Pageable
    ): Page<TopicView> {
        return service.getTopics(courseName, authorName, pagination)
    }

    @GetMapping("/{id}")
    fun getTopic(@PathVariable id: Long): TopicView {
        return service.getTopicViewById(id)
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
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
    @CacheEvict(value = ["topics"], allEntries = true)
    fun updateTopic(@RequestBody @Valid form: UpdateTopicForm): TopicView {
        return service.updateTopic(form)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun deleteTopic(@PathVariable id: Long) {
        service.deleteTopic(id)
    }

    @GetMapping("report")
    fun report(): List<TopicByCategoryDto> {
        return service.report()
    }

    @GetMapping("no-answer-report")
    fun noAnswerReport(): List<Topic> {
        return service.noAnswerReport()
    }
}