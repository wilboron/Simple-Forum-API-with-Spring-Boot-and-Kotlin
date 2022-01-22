package com.gmail.williammingardi.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NewAnswerForm(
    var id: Long?,
    @field:NotEmpty
    @field:Size(min = 5, max = 255)
    val message: String,
    @field:NotNull(message = "Missing authorId")
    val authorId: Long,
    @field:NotNull
    val topicId: Long,
    val solution: Boolean = false
)