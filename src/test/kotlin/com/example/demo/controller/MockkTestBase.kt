package com.example.demo.controller

import com.example.demo.repository.ArticleRepository
import com.example.demo.repository.UsersRepository
import com.ninjasquad.springmockk.MockkBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc

open class MockkTestBase(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var userRepository: UsersRepository

    @MockkBean
    lateinit var articleRepository: ArticleRepository
}
