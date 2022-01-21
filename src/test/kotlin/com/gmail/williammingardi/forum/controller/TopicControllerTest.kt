package com.gmail.williammingardi.forum.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class TopicControllerTest @Autowired constructor(
    val mockMvc: MockMvc
) {

    val baseUrl = "/topics"

    @Nested
    @DisplayName("GET /topics")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetTopics {

        @Test
        fun `should return all banks`() {
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].title") { value("Duvida Kotlin") }
                    jsonPath("$[0].id") { value(1) }
                }
        }
    }

    @Nested
    @DisplayName("GET /topics/{id}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetTopic {

        @Test
        fun `should return a bank with id provided`() {
            mockMvc.get("$baseUrl/2")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.title") { value("Ajuda com Init") }
                    jsonPath("$.id") { value(2) }
                }
        }
    }

    @Nested
    @DisplayName("GET /topics/{id}/answers")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetTopicAnswers {

        @Test
        fun `should return a bank with id provided`() {
            mockMvc.get("$baseUrl/3/answers")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].message") { value("Teste resposta") }
                    jsonPath("$[0].solution") { value(true) }
                }
        }
    }
}