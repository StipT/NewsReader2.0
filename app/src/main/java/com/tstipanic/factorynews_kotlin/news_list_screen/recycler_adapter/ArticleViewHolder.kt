package com.tstipanic.factorynews_kotlin.news_list_screen.recycler_adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.tstipanic.factorynews_kotlin.model.data.Article
import com.tstipanic.factorynews_kotlin.news_list_screen.view.OnClickedListener

import kotlinx.android.synthetic.main.recycler_item.view.*


class ArticleViewHolder(
     view: View,
    private val onClickedListener: OnClickedListener
) : RecyclerView.ViewHolder(view), View.OnClickListener {


    fun onBind(articleList: List<Article>, position: Int) {
        val articleItem = articleList[position]
        itemView.itemTitle.text = articleItem.title
        Glide.with(itemView).load(articleItem.urlToImage).into(itemView.itemImage)
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        onClickedListener.onClicked(adapterPosition)
    }
}