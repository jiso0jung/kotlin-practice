package com.example.demo.controller

import com.example.demo.domain.Members
import com.example.demo.repository.MembersRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/redis-test")
class MembersController(private val membersRepository: MembersRepository) {

    @GetMapping("/members")
    fun getMembersList(): ResponseEntity<Any> =
        ResponseEntity.ok(membersRepository.findAll())

    @PostMapping("/member")
    fun setMembers(@RequestBody members: Members): ResponseEntity<Any> {
        membersRepository.save(members)
        return ResponseEntity.created(URI.create("/member")).build()
    }
}
