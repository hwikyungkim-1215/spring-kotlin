package com.spring.kotlin

import com.spring.kotlin.Article
import com.spring.kotlin.ArticleRepository
import com.spring.kotlin.User
import com.spring.kotlin.format
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

// HtmlController형식이 지정된 날짜로 블로그 및 기사 페이지를 렌더링하기 위해 업데이트 함
// 생성자 매개변수는 단일 생성자(암시적) ArticleRepository를 MarkdownConverter가지므로 자동으로 자동 연결된다.
@Controller
class HtmlController (private val repository: ArticleRepository,
                      private val properties: BlogProperties) {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = properties.title
        model["banner"] = properties.banner
        model["articles"] = repository.findAllByOrderByAddedAtDesc().map {
            it.render()
        }
        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String{
        val article = repository
                .findBySlug(slug)
                ?. run{ this.render() }
                ?:throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")
        model["title"] = article.title
        model["article"] = article
        return "article"
    }

    fun Article.render() = RenderArticle(
            slug,
            title,
            headline,
            content,
            author,
            addedAt.format()
    )

    data class RenderArticle(
            val slug: String,
            val title: String,
            val headline: String,
            val content: String,
            val author: User,
            val addedAt: String
    )
}