package com.gmail.williammingardi.forum.service

import com.gmail.williammingardi.forum.exception.NotFoundException
import com.gmail.williammingardi.forum.model.User
import com.gmail.williammingardi.forum.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository
) : UserDetailsService {

    fun getUserById(id: Long): User {
        return repository.findByIdOrNull(id)
            ?: throw NotFoundException("User", id)
    }

    override fun loadUserByUsername(username: String?): UserDetails? {
        val user = repository.findByEmail(username) ?: throw RuntimeException()
        return UserDetail(user)
    }
}
