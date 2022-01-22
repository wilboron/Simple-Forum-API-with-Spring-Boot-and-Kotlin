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
internal class AnswerControllerTest @Autowired constructor(
    val mockMvc: MockMvc
) {

    val baseUrl = "/topics"

    @Nested
    @DisplayName("GET /topics/{id}/answers")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetTopicAnswers {

        @Test
        fun `should return answers with topic id provided`() {
            mockMvc.get("$baseUrl/1/answers")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].message") { value("Resposta 1") }
                    jsonPath("$[0].solution") { value(false) }
                }
        }
    }
}