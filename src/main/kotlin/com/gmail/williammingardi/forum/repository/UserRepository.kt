package com.gmail.williammingardi.forum.repository;

import com.gmail.williammingardi.forum.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(username: String?): User?
}