package com.tstipanic.factorynews_kotlin.news_list_screen.recycler_adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tstipanic.factorynews_kotlin.R
import com.tstipanic.factorynews_kotlin.model.data.Article
import com.tstipanic.factorynews_kotlin.news_list_screen.view.OnClickedListener

class RecyclerAdapter(private val onClickedListener: OnClickedListener) : RecyclerView.Adapter<ArticleViewHolder>() {

    private var articleList: MutableList<Article> = mutableListOf()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ArticleViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.recycler_item, p0, false)
        return ArticleViewHolder(view, onClickedListener)
    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(p0: ArticleViewHolder, p1: Int) {
        p0.onBind(articleList, p1)
    }

    fun addItems(list: List<Article>) {
        articleList.clear()
        articleList = list.toMutableList()
        notifyDataSetChanged()
    }
}