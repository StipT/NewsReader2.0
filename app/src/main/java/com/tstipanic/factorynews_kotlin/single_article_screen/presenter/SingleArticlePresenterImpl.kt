package com.tstipanic.factorynews_kotlin.single_article_screen.presenter

import com.tstipanic.factorynews_kotlin.model.interactor.Interactor
import com.tstipanic.factorynews_kotlin.single_article_screen.view.SingleArticleView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class SingleArticlePresenterImpl(
    private val singleArticleView: SingleArticleView,
    private val interactor: Interactor
) : SingleArticlePresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate() {
        singleArticleView.viewPagerSetUp(interactor.getNewsFromRealm())
        intervalScreenChange()
    }

    override fun onStop() {
        if (compositeDisposable.isDisposed.not()) {
            compositeDisposable.dispose()
        }
    }

    private fun intervalScreenChange() {
        val disposable = Observable
            .interval(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (singleArticleView.isLastArticle()) compositeDisposable.dispose()
                else singleArticleView.changeScreen()
            },
                { e -> e.printStackTrace() }
            )
        compositeDisposable.add(disposable)
    }
}