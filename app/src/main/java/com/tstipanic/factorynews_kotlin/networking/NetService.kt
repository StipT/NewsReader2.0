package com.tstipanic.factorynews_kotlin.networking

import android.content.Context
import com.tstipanic.factorynews_kotlin.common.BASE_URL
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NetService(context: Context) {

    private val okHttpCache: Cache = Cache(context.cacheDir, 10 * 1024 * 1024)

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().cache(okHttpCache).build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

     val apiService: ApiService = retrofit.create()
}