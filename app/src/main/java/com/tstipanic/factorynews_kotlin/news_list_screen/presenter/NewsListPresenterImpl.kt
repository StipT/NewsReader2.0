package com.tstipanic.factorynews_kotlin.news_list_screen.presenter


import com.tstipanic.factorynews_kotlin.model.data.Article
import com.tstipanic.factorynews_kotlin.model.interactor.Interactor
import com.tstipanic.factorynews_kotlin.news_list_screen.view.NewsListView


class NewsListPresenterImpl(
    private val newsListView: NewsListView,
    private val interactor: Interactor
) : NewsListPresenter, Interactor.OnFinishedListener {

    override fun onCreate() {
        newsListView.showProgress()
        newsListView.bindRecyclerView()
        interactor.fetchNews(this)
        newsListView.hideProgress()

        if (newsListView.isOnBoardingCompleted().not()) {
            newsListView.showOnBoarding()
        }
    }

    override fun onFinished(list: List<Article>) {
        newsListView.updateItems(list)
    }

    override fun onResume() {
    }

    override fun onDestroy() {
        interactor.disposeDisposable()
        interactor.closeRealm()
    }
}