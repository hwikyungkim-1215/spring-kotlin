package com.spring.kotlin

import org.springframework.data.repository.CrudRepository

//  Spring Data JPA 저장소를 선언
interface ArticleRepository : CrudRepository<Article, Long> {
    fun findBySlug(slug: String): Article?
    fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}

interface UserRepository : CrudRepository<User, Long> {
    fun findByLogin(login: String): User?
}