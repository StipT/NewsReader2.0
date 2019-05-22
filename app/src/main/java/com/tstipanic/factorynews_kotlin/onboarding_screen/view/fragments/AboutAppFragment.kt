package com.tstipanic.factorynews_kotlin.onboarding_screen.view.fragments

import com.tstipanic.factorynews_kotlin.R
import com.tstipanic.factorynews_kotlin.onboarding_screen.view.base.BaseFragment


class AboutAppFragment: BaseFragment() {

    override fun getLayoutResourceId() = R.layout.fragment_about_app

    companion object {
        fun newInstance() = AboutAppFragment()
    }
}