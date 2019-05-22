package com.tstipanic.factorynews_kotlin.model.interactor

import android.content.ContentValues.TAG
import android.util.Log
import com.tstipanic.factorynews_kotlin.model.data.Article
import com.tstipanic.factorynews_kotlin.networking.NetService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm


interface Interactor {

    interface OnFinishedListener {
        fun onFinished(list: List<Article>)
    }

    fun storeNewsOnRealm(list: List<Article>)

    fun getNewsFromRealm(): List<Article>

    fun fetchNews(onFinishedListener: OnFinishedListener)

    fun disposeDisposable()

    fun closeRealm()
}

class InteractorImpl(private val netService: NetService) : Interactor {

    private val realm = Realm.getDefaultInstance()
    private val compositeDisposable = CompositeDisposable()


    override fun storeNewsOnRealm(list: List<Article>) {
        realm.executeTransaction { realm ->
            realm.deleteAll()
            realm.copyToRealmOrUpdate(list) }
    }

    override fun getNewsFromRealm(): List<Article> {
        var list = mutableListOf<Article>()

        realm.executeTransaction { realm ->
            val query = realm.where(Article::class.java).findAllAsync()
            list = realm.copyFromRealm(query)
        }
        return list
    }

    override fun fetchNews(onFinishedListener: Interactor.OnFinishedListener) {

        val newsResponse = netService.apiService.getNews()

        val disposable: Disposable = newsResponse
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {

                val news = it.body()
                val articleList = news?.articles
                storeNewsOnRealm(articleList ?: listOf())
                onFinishedListener.onFinished(articleList ?: listOf())
                articleList

            }
            .subscribe({ Log.d(TAG, "Success") }, { it.printStackTrace() })

        compositeDisposable.add(disposable)
    }

    override fun disposeDisposable() {
        if (compositeDisposable.isDisposed.not()) {
            compositeDisposable.dispose()
        }
    }

    override fun closeRealm() {
        if (!realm.isClosed) {
            realm.close()
        }
    }
}