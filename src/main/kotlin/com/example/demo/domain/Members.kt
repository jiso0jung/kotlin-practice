package com.example.demo.domain

import org.springframework.data.redis.core.RedisHash
import javax.persistence.Id

@RedisHash("members")
data class Members(
    val name: String? = null,
    val email: String? = null,
    var age: Int? = null,
    @Id val id: String? = null,
)
