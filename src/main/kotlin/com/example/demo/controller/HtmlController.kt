package com.example.demo.controller

import com.example.demo.DemoProperties
import com.example.demo.entity.Article
import com.example.demo.entity.Users
import com.example.demo.format
import com.example.demo.repository.ArticleRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Controller
class HtmlController(private val repository: ArticleRepository, private val properties: DemoProperties) {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = properties.title ?: "Empty title"
        model["banner"] = properties.banner!!
        model["articles"] = repository.findAllByOrderByAddedAtDesc().map {
            it.render()
        }

        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        val article = repository.findBySlug(slug)?.render() ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "This article does not exist"
        )

        model["title"] = article.title
        model["article"] = article

        return "article"
    }

    fun Article.render() = RenderedArticle(
        slug,
        title,
        headline,
        content,
        author,
        addedAt.format()
    )

    data class RenderedArticle(
        val slug: String,
        val title: String,
        val headline: String,
        val content: String,
        val author: Users,
        val addedAt: String
    )
}
