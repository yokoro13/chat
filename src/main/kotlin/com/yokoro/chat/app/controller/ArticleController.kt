package com.yokoro.chat.app.controller

import com.yokoro.chat.app.request.ArticleRequest
import com.yokoro.chat.domain.entity.Article
import com.yokoro.chat.domain.repository.ArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
class ArticleController {

    @Autowired
    lateinit var articleRepository: ArticleRepository

    @PostMapping("/")
    // パラメータは自動で紐付けされる
    fun resisterArticle(@ModelAttribute articleRequest: ArticleRequest): String{
        articleRepository.save(
                Article(0, articleRequest.name, articleRequest.title, articleRequest.contents, articleRequest.articleKey)
        )
        return "redirect:/"
    }

    @GetMapping("/")
    fun getArticleList(model: Model): String{
        model.addAttribute("articles", articleRepository.findAll())
        return "index"
    }

    @GetMapping("/edit/{id}")
    fun getArticleEdit(@PathVariable id: Int, model: Model): String{
        return if (articleRepository.existsById(id)){
            model.addAttribute("article", articleRepository.findById(id))
            "edit"
        } else {
            "redirect:/"
        }
    }

    @PostMapping("/update")
    fun updateArticle(articleRequest: ArticleRequest): String{
        if (!articleRepository.existsById(articleRequest.id)){
            return "redirect:/"
        }

        val article: Article = articleRepository.findById(articleRequest.id).get()
        if (articleRequest.articleKey != article.articleKey){
            return "redirect:/edit/${articleRequest.id}"
        }

        article.name = articleRequest.name
        article.title = articleRequest.title
        article.contents = articleRequest.contents
        article.updateAt = Date()

        articleRepository.save(article)

        return "redirect:/"
    }
}