package com.gmail.williammingardi.forum.exception

open class NotFoundException(
    class_name: String,
    id: Long
) : RuntimeException() {
    override var message = "$class_name not found with id '$id'"
}