package com.gmail.williammingardi.forum.service

import com.gmail.williammingardi.forum.model.User
import org.springframework.stereotype.Service

@Service
class UserService(final var users: List<User>) {

    init {
        val user = User(
            id = 1,
            name = "Ana Paula",
            email = "ana@gmail.com"
        )

        users = listOf(user)
    }

    fun getUser(id: Long): User {
        return users.firstOrNull { it.id == id }
            ?: throw IllegalAccessError()
    }
}
