package com.gmail.williammingardi.forum.repository;

import com.gmail.williammingardi.forum.model.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository : JpaRepository<Course, Long> {
}