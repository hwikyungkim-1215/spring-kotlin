package com.spring.kotlin

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// DB에 데이터 넣기
@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            articleRepository: ArticleRepository) =
            ApplicationRunner{
                val user = userRepository.save(User("login", "firstName", "lastName"))
                articleRepository.save(Article("title1", "headline1", "content1", user))
                articleRepository.save(Article("title2", "headline2", "content2", user))
            }
}