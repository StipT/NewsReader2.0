package com.tstipanic.factorynews_kotlin.splash_screen.presenter

import com.tstipanic.factorynews_kotlin.splash_screen.view.SplashView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class SplashPresenterImpl(val view: SplashView) : SplashPresenter {
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate() {
        val intervalDisposable = Observable
            .interval(30, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ view.notifyProgressBar() }, { it.printStackTrace() })

        val timerDisposable = Observable
            .timer(3, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ view.goToNewsListActivity() }, { it.printStackTrace() })

        compositeDisposable.addAll(intervalDisposable, timerDisposable)
    }

    override fun onStop() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }


    }
}