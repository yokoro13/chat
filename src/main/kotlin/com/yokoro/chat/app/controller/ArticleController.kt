package com.yokoro.chat.app.controller

import com.yokoro.chat.app.request.ArticleRequest
import com.yokoro.chat.domain.entity.Article
import com.yokoro.chat.domain.repository.ArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ArticleController {

    @Autowired
    lateinit var articleRepository: ArticleRepository

    @PostMapping("/")
    @ResponseBody
    // パラメータは自動で紐付けされる
    fun resisterArticle(@ModelAttribute articleRequest: ArticleRequest): String{
        articleRepository.save(
                Article(0, articleRequest.name, articleRequest.title, articleRequest.contents, articleRequest.articleKey)
        )
        return "Saved"
    }
}