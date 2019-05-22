package com.tstipanic.factorynews_kotlin.splash_screen.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tstipanic.factorynews_kotlin.R
import com.tstipanic.factorynews_kotlin.news_list_screen.view.NewsListActivity
import com.tstipanic.factorynews_kotlin.splash_screen.presenter.SplashPresenter
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class SplashActivity : AppCompatActivity(), SplashView {

    private val splashPresenter: SplashPresenter by inject { parametersOf(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        splashPresenter.onCreate()
    }

    override fun goToNewsListActivity() {
        intent = Intent(applicationContext, NewsListActivity::class.java)
        startActivity(intent)
        finish()
    }


    override fun notifyProgressBar() {
    progressBarSplash.progress += 1
    }
}
