package com.tstipanic.factorynews_kotlin.single_article_screen.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.tstipanic.factorynews_kotlin.common.EXTRA_ARTICLE_ITEM
import com.tstipanic.factorynews_kotlin.R
import com.tstipanic.factorynews_kotlin.model.data.Article
import kotlinx.android.synthetic.main.fragment_pager.view.*


class PagerFragment : Fragment() {

    companion object {

        fun newInstance(articleList: List<Article>, i: Int): PagerFragment {
            val bundle = Bundle()
            val fragment = PagerFragment()
            val articleItem: Article = articleList[i]
            bundle.putSerializable(EXTRA_ARTICLE_ITEM, articleItem)
            fragment.arguments = bundle
            return fragment

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val articleItem: Article = arguments?.getSerializable(EXTRA_ARTICLE_ITEM) as Article
        val view: View = inflater.inflate(R.layout.fragment_pager, container, false)

        view.pagerTitle?.text = articleItem.title
        view.pagerDescription?.text = articleItem.description
        Glide.with(this).load(articleItem.urlToImage).into(view.pagerImage)
        return view
    }
}