package com.tstipanic.factorynews_kotlin.single_article_screen.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tstipanic.factorynews_kotlin.common.EXTRA_ITEM_POSITION
import com.tstipanic.factorynews_kotlin.common.PAGE_LIMIT
import com.tstipanic.factorynews_kotlin.R
import com.tstipanic.factorynews_kotlin.model.data.Article
import com.tstipanic.factorynews_kotlin.single_article_screen.pager_adapter.CustomPagerAdapter
import com.tstipanic.factorynews_kotlin.single_article_screen.presenter.SingleArticlePresenter
import kotlinx.android.synthetic.main.activity_single_article.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SingleArticleActivity : AppCompatActivity(), SingleArticleView {


    private val singleArticlePresenterImp: SingleArticlePresenter by inject{ parametersOf(this)}

    private val customPagerAdapter: CustomPagerAdapter by inject { parametersOf(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_article)
        setSupportActionBar(singleArticleToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        setTheme(R.style.AppTheme)
        singleArticlePresenterImp.onCreate()
    }

    override fun viewPagerSetUp(articleList: List<Article>) {
        customPagerAdapter.setItems(articleList)
        viewPager.adapter = customPagerAdapter
        dotsIndicatiorSingleArticle.setViewPager(viewPager)

        var position = intent.getIntExtra(EXTRA_ITEM_POSITION, 0)
        viewPager.currentItem = position
    }

    override fun changeScreen() {
        viewPager.currentItem++
    }

    override fun isLastArticle(): Boolean = (viewPager.currentItem >= PAGE_LIMIT)

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}