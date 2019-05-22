package com.tstipanic.factorynews_kotlin.di

import android.app.Application
import android.support.v4.app.FragmentManager
import com.tstipanic.factorynews_kotlin.model.interactor.Interactor
import com.tstipanic.factorynews_kotlin.model.interactor.InteractorImpl
import com.tstipanic.factorynews_kotlin.networking.NetService
import com.tstipanic.factorynews_kotlin.news_list_screen.presenter.NewsListPresenter
import com.tstipanic.factorynews_kotlin.news_list_screen.presenter.NewsListPresenterImpl
import com.tstipanic.factorynews_kotlin.news_list_screen.recycler_adapter.RecyclerAdapter
import com.tstipanic.factorynews_kotlin.news_list_screen.view.NewsListView
import com.tstipanic.factorynews_kotlin.news_list_screen.view.OnClickedListener
import com.tstipanic.factorynews_kotlin.single_article_screen.pager_adapter.CustomPagerAdapter
import com.tstipanic.factorynews_kotlin.single_article_screen.presenter.SingleArticlePresenter
import com.tstipanic.factorynews_kotlin.single_article_screen.presenter.SingleArticlePresenterImpl
import com.tstipanic.factorynews_kotlin.single_article_screen.view.SingleArticleView
import com.tstipanic.factorynews_kotlin.splash_screen.presenter.SplashPresenter
import com.tstipanic.factorynews_kotlin.splash_screen.presenter.SplashPresenterImpl
import com.tstipanic.factorynews_kotlin.splash_screen.view.SplashView
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val serviceModule = module {


    single<NetService> { NetService(androidContext() as Application) }

    factory<Interactor> { InteractorImpl(get()) }
}

val splashActivityModule = module {
    factory<SplashPresenter> { (view: SplashView) -> SplashPresenterImpl(view) }
}

val newsListActivityModule = module {

    factory<NewsListPresenter> { (view: NewsListView) -> NewsListPresenterImpl(view, get()) }

    factory<RecyclerAdapter> { (listener: OnClickedListener) -> RecyclerAdapter(listener) }

}

val singleArticleActivityModule = module {


    factory<SingleArticlePresenter> { (view: SingleArticleView) -> SingleArticlePresenterImpl(view, get()) }

    factory<CustomPagerAdapter> { (fm: FragmentManager) -> CustomPagerAdapter(fm) }

}







