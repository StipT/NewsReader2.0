package com.tstipanic.factorynews_kotlin.model.data

data class News (
    var status: String,
    var source: String,
    var sortBy: String,
    var articles: List<Article>
)