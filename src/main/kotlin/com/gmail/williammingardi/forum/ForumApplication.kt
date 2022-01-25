package com.gmail.williammingardi.forum

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import java.time.ZoneOffset
import java.util.*

@SpringBootApplication
@EnableCaching
class ForumApplication

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC.id));
    runApplication<ForumApplication>(*args)
}
