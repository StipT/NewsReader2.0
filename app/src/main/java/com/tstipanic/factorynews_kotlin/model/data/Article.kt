package com.tstipanic.factorynews_kotlin.model.data


import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Article(
    var author: String = "",
    var title: String = "",
    var description: String = "",
    var url: String = "",
    var urlToImage: String = "",
    var publishedAt: String = ""
) : RealmObject(), Serializable



