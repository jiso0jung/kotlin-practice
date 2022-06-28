package com.example.demo.config

import com.example.demo.entity.Article
import com.example.demo.entity.Users
import com.example.demo.repository.ArticleRepository
import com.example.demo.repository.UsersRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InitDataConfig {

    @Bean
    fun databaseInitializer(usersRepository: UsersRepository,
                            articleRepository: ArticleRepository) = ApplicationRunner {

        val smaldini = usersRepository.save(Users("smaldini", "Stephane", "Maldini"))

        articleRepository.save(Article(
                title = "Reactor Bismuth is out",
                headline = "Lorem ipsum",
                content = "dolor sit amet",
                author = smaldini
        ))
        articleRepository.save(Article(
                title = "Reactor Aluminium has landed",
                headline = "Lorem ipsum",
                content = "dolor sit amet",
                author = smaldini
        ))
    }

}