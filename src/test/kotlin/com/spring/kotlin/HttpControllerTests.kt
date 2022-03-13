package com.spring.kotlin


import com.spring.kotlin.Article
import com.spring.kotlin.ArticleRepository
import com.spring.kotlin.User
import com.spring.kotlin.UserRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class HttpsControllersTests(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var articleRepository: ArticleRepository

    @MockkBean
    private lateinit var userRepository: UserRepository

    @Test
    fun `List articles`() {
        val user = User("login", "firstName", "lastName")
        val article1 = Article("title1", "headline1", "content1", user)
        val article2 = Article("title2", "headline2", "content2", user)

        every { articleRepository.findAllByOrderByAddedAtDesc() } returns
                listOf(article1, article2)

        mockMvc.perform(get("/api/articles/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].author.login").value(user.login))
                .andExpect(jsonPath("\$.[0].slug").value(article1.slug))
                .andExpect(jsonPath("\$.[1].author.login").value(article2.author.login))
                .andExpect(jsonPath("\$.[1].slug").value(article2.slug))
    }

    @Test
    fun `List users`() {
        val user1 = User("login1", "firstName1", "lastName1")
        val user2 = User("login2", "firstName2", "lastName2")

        every { userRepository.findAll() } returns listOf(user1, user2)

        mockMvc.perform(get("/api/users/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].login").value(user1.login))
                .andExpect(jsonPath("\$.[1].login").value(user2.login))
    }

}