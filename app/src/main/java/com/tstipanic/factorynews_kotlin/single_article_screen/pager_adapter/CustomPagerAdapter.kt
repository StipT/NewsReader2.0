package com.tstipanic.factorynews_kotlin.single_article_screen.pager_adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.tstipanic.factorynews_kotlin.model.data.Article
import com.tstipanic.factorynews_kotlin.single_article_screen.view.PagerFragment

class CustomPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {


    private var articleList: List<Article> = mutableListOf()


    fun setItems(list: List<Article>) { articleList = list }

    override fun getItem(p0: Int): Fragment = PagerFragment.newInstance(articleList , p0)

    override fun getCount(): Int = articleList.size

}