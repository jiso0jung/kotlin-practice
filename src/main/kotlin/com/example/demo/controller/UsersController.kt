package com.example.demo.controller

import com.example.demo.domain.Users
import com.example.demo.repository.UsersRepository
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/user")
class UsersController(private val repository: UsersRepository) {

    @GetMapping("/")
    fun findAll(): Iterable<Users> = repository.findAll()

    @GetMapping("/{login}")
    fun findOne(@PathVariable login: String) =
        repository.findByLogin(login) ?: throw ResponseStatusException(NOT_FOUND, "This user does not exist")
}
