package com.gmail.williammingardi.forum.exception

open class NotFoundException(
    message: String?
) : RuntimeException(message) {
    constructor() : this("Entity Not Found")
}