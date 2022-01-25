package com.gmail.williammingardi.forum.dto

import com.gmail.williammingardi.forum.model.TopicStatus
import java.time.OffsetDateTime

data class TopicView(
    val id: Long?,
    val title: String,
    val message: String,
    val status: TopicStatus,
    val createdAt: OffsetDateTime
)
