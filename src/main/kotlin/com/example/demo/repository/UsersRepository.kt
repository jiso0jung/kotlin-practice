package com.example.demo.repository

import com.example.demo.entity.Users
import org.springframework.data.repository.CrudRepository

interface UsersRepository : CrudRepository<Users, Long> {
    fun findByLogin(login: String): Users?
}
