package com.gmail.williammingardi.forum.model

import java.time.LocalDateTime
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
class Answer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val message: String,
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
    @ManyToOne
    val author: User,
    @ManyToOne
    val topic: Topic,
    val solution: Boolean
)
