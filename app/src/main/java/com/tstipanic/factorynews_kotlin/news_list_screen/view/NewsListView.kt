package com.tstipanic.factorynews_kotlin.news_list_screen.view

import com.tstipanic.factorynews_kotlin.model.data.Article

interface NewsListView {

    fun showProgress()

    fun hideProgress()

    fun bindRecyclerView()

    fun updateItems(articleList: List<Article>)

    fun goToSingleArticleActivity(position: Int)

    fun isOnBoardingCompleted(): Boolean

    fun showOnBoarding()

}