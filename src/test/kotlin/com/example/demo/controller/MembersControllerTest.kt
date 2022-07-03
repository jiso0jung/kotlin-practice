@file:Suppress("SpringJavaInjectionPointsAutowiringInspection")

package com.example.demo.controller

import com.example.demo.domain.Members
import com.google.gson.Gson
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
class MembersControllerTest @Autowired constructor(mockMvc: MockMvc) : MockkTestBase(mockMvc) {

    @Test
    fun getMemberList() {
        val members1 = Members("americano", "americano@coffee.com", 20, "1")
        val members2 = Members("cafelatte", "cafelatte@coffee.com", 21, "2")

        every { membersRepository.findAll() } returns listOf(members1, members2)

        mockMvc.perform(MockMvcRequestBuilders.get("/redis-test/members/").accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].name").value(members1.name))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].id").value(members1.id))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[1].name").value(members2.name))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[1].id").value(members2.id))
    }

    @Test
    fun setMember() {
        val members1 = Members("americano", "americano@coffee.com", 20, "1")

        every { membersRepository.save(members1) } returns members1

        mockMvc.perform(
            MockMvcRequestBuilders.post("/redis-test/member/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Gson().toJson(members1))
        ).andExpect(MockMvcResultMatchers.status().isCreated)
    }
}
