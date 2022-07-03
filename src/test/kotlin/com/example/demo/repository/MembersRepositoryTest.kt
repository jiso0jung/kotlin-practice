@file:Suppress("SpringJavaInjectionPointsAutowiringInspection")

package com.example.demo.repository

import com.example.demo.RedisContainerConfig
import com.example.demo.domain.Members
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MembersRepositoryTest @Autowired constructor(
    private val membersRepository: MembersRepository
) : RedisContainerConfig() {

    @Test
    fun findAll() {
        // given
        val members1 = Members("americano", "americano@coffee.com", 20, "1")
        val members2 = Members("cafelatte", "cafelatte@coffee.com", 21, "2")
        membersRepository.save(members1)
        membersRepository.save(members2)

        // when
        val result = membersRepository.findAll()

        // then
        assertThat(result).contains(members1, members2)
    }

    @Test
    fun `save and find`() {
        // given
        val members1 = Members("americano", "americano@coffee.com", 20, "1")

        // when
        membersRepository.save(members1)

        // then
        val member = membersRepository.findById(members1.id!!)
        assertThat(member.get()).isEqualTo(members1)
    }
}
