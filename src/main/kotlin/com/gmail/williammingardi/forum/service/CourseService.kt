package com.gmail.williammingardi.forum.service

import com.gmail.williammingardi.forum.model.Course
import org.springframework.stereotype.Service

@Service
class CourseService(final var courses: List<Course>) {

    init {
        val course = Course(
            id = 1,
            name = "Kotlin",
            category = "Programação"
        )

        courses = listOf(course)
    }

    fun getCourse(id: Long): Course {
        return courses.firstOrNull { it.id == id }
            ?: throw IllegalAccessError()
    }

}
