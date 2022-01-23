package com.gmail.williammingardi.forum.repository

import com.gmail.williammingardi.forum.model.Topic
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository : JpaRepository<Topic, Long> {
}