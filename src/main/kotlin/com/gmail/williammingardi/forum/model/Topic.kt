package com.gmail.williammingardi.forum.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Topic(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var message: String,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    var course: Course,
    @ManyToOne
    var author: User,
    @Enumerated(value = EnumType.STRING)
    var status: TopicStatus = TopicStatus.NO_ANSWER,
    @OneToMany(mappedBy = "topic")
    var answers: List<Answer> = mutableListOf()
)