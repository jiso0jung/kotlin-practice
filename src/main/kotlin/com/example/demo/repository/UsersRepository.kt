package com.example.demo.repository

import com.example.demo.domain.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository : JpaRepository<Users, Long> {
    fun findByLogin(login: String): Users?
}
