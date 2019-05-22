package com.tstipanic.factorynews_kotlin


import android.app.Application
import com.tstipanic.factorynews_kotlin.di.serviceModule
import com.tstipanic.factorynews_kotlin.di.newsListActivityModule
import com.tstipanic.factorynews_kotlin.di.singleArticleActivityModule
import com.tstipanic.factorynews_kotlin.di.splashActivityModule
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MyApp: Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            androidFileProperties()
            modules(serviceModule, splashActivityModule, newsListActivityModule, singleArticleActivityModule)
        }

        Realm.init(this)

        val realmConfig = RealmConfiguration.Builder()
            .name("news.realm")
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(realmConfig)
    }


}