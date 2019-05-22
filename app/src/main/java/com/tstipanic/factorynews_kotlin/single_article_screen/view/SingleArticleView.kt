package com.tstipanic.factorynews_kotlin.single_article_screen.view

import com.tstipanic.factorynews_kotlin.model.data.Article

interface SingleArticleView {
    fun viewPagerSetUp(articleList: List<Article>)

    fun changeScreen()

    fun isLastArticle(): Boolean
}