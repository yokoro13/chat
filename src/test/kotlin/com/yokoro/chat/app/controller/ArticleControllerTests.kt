package com.yokoro.chat.app.controller

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders


@SpringBootTest
class ArticleControllerTests {
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var target: ArticleController

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(target).build()
    }

    @Test
    fun registerArticleTest(){
        mockMvc.perform(
                MockMvcRequestBuilders.post("/")
                        .param("name", "testName")
                        .param("title", "testTitle")
                        .param("contents", "testContents")
                        .param("articleKey", "testKey")
        )
                .andExpect(status().is3xxRedirection)
                .andExpect(view().name("redirect:/"))
    }

    @Test
    fun getArticleListTest(){
        mockMvc.perform(
                MockMvcRequestBuilders.get("/")
        )
                .andExpect(status().isOk)
                .andExpect(model().attributeExists("articles"))
                .andExpect(view().name("index"))
    }
}