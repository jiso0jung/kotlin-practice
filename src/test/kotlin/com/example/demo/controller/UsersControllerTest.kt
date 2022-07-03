@file:Suppress("SpringJavaInjectionPointsAutowiringInspection")

package com.example.demo.controller

import com.example.demo.domain.Users
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class UsersControllerTest @Autowired constructor(mockMvc: MockMvc) : MockkTestBase(mockMvc) {

    @Test
    fun `List users`() {
        val juergen = Users("springjuergen", "Juergen", "Hoeller")
        val smaldini = Users("smaldini", "Stephane", "Maldini")

        every { userRepository.findAll() } returns listOf(juergen, smaldini)

        mockMvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].login").value(juergen.login))
            .andExpect(jsonPath("\$.[1].login").value(smaldini.login))
    }
}
