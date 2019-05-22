package com.tstipanic.factorynews_kotlin.onboarding_screen.view.fragments

import android.os.Bundle
import android.view.View
import com.tstipanic.factorynews_kotlin.R
import com.tstipanic.factorynews_kotlin.onboarding_screen.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_about_mentor.*

class AboutMentorFragment: BaseFragment(){

    override fun getLayoutResourceId() = R.layout.fragment_about_mentor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initUi() {
        cancelImage.setOnClickListener{activity?.finish()}
    }

    companion object {
        fun newInstance() = AboutMentorFragment()
    }
}