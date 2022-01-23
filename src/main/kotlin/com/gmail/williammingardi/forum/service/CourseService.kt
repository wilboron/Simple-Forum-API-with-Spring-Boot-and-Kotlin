package com.gmail.williammingardi.forum.service

import com.gmail.williammingardi.forum.exception.NotFoundException
import com.gmail.williammingardi.forum.model.Course
import com.gmail.williammingardi.forum.repository.CourseRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CourseService(private val repository: CourseRepository) {

    fun getCourseById(id: Long): Course {
        return repository.findByIdOrNull(id)
            ?: throw NotFoundException("Course", id)
    }

}
