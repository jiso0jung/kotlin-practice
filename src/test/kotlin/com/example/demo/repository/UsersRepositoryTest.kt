@file:Suppress("SpringJavaInjectionPointsAutowiringInspection")

package com.example.demo.repository

import com.example.demo.domain.Users
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@AutoConfigureTestDatabase(replace = NONE)
@DataJpaTest
class UsersRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val usersRepository: UsersRepository
) {

    @Test
    fun `When findByLogin then return User`() {
        val juergen = Users("springjuergen", "Juergen", "Hoeller")
        entityManager.persist(juergen)
        entityManager.flush()
        val user = usersRepository.findByLogin(juergen.login)
        assertThat(user).isEqualTo(juergen)
    }
}
