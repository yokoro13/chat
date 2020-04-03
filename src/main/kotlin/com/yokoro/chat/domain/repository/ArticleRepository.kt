package com.yokoro.chat.domain.repository

import com.yokoro.chat.domain.entity.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : JpaRepository<Article, Int>
