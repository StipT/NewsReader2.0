package com.tstipanic.factorynews_kotlin.networking

import com.tstipanic.factorynews_kotlin.common.URL_EXT
import com.tstipanic.factorynews_kotlin.model.data.News
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("Cache-Control: max-age=300")
    @GET(URL_EXT)
    fun getNews(): Single<Response<News>>
}