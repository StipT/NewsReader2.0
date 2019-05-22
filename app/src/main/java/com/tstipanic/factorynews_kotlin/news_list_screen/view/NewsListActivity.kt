package com.tstipanic.factorynews_kotlin.news_list_screen.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.tstipanic.factorynews_kotlin.common.EXTRA_ITEM_POSITION
import com.tstipanic.factorynews_kotlin.R
import com.tstipanic.factorynews_kotlin.R.*
import com.tstipanic.factorynews_kotlin.common.ONBOARDING_COMPLETED
import com.tstipanic.factorynews_kotlin.model.data.Article
import com.tstipanic.factorynews_kotlin.map_screen.MapsActivity
import com.tstipanic.factorynews_kotlin.news_list_screen.presenter.NewsListPresenter
import com.tstipanic.factorynews_kotlin.news_list_screen.recycler_adapter.RecyclerAdapter
import com.tstipanic.factorynews_kotlin.onboarding_screen.view.OnBoardingActivity
import com.tstipanic.factorynews_kotlin.single_article_screen.view.SingleArticleActivity
import kotlinx.android.synthetic.main.activity_news_list.*
import kotlinx.android.synthetic.main.activity_news_list.newsListToolbar
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


interface OnClickedListener {
    fun onClicked(position: Int)
}

class NewsListActivity : AppCompatActivity(), NewsListView, OnClickedListener {

    private val newsListPresenterImpl: NewsListPresenter by inject { parametersOf(this) }
    private val recyclerAdapter: RecyclerAdapter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_news_list)

        setSupportActionBar(newsListToolbar)
        newsListPresenterImpl.onCreate()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        id.google_maps -> {startActivity(Intent(this, MapsActivity::class.java)); true}
        else -> { super.onOptionsItemSelected(item)}
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.news_list_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()
        newsListPresenterImpl.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        newsListPresenterImpl.onDestroy()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
        progressBar.bringToFront()
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun bindRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter
        Log.d("NewsListActivity", "Recycler view bound")
    }

    override fun updateItems(articleList: List<Article>) {
        recyclerAdapter.addItems(articleList)
        Log.d("NewsListActivity", "Recycler view UPDATED")
    }

    override fun goToSingleArticleActivity(position: Int) {
        val intent = Intent(this, SingleArticleActivity::class.java)
        intent.putExtra(EXTRA_ITEM_POSITION, position)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onClicked(position: Int) {
        goToSingleArticleActivity(position)
    }

   override fun isOnBoardingCompleted() = PreferenceManager.getDefaultSharedPreferences(this).contains(ONBOARDING_COMPLETED)


    override fun showOnBoarding() {
        startActivity(Intent(this, OnBoardingActivity::class.java))
//        PreferenceManager.getDefaultSharedPreferences(this).edit().apply{
//            putBoolean(ONBOARDING_COMPLETED, true)
//            apply()
        }
    }

