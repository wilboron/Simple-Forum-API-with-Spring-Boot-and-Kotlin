package com.gmail.williammingardi.forum.dto

import java.time.OffsetDateTime

data class AnswerView(
    val id: Long?,
    val message: String,
    val createdAt: OffsetDateTime,
    val authorId: Long?,
    val topicId: Long?,
    val solution: Boolean
)
