package com.tstipanic.factorynews_kotlin.onboarding_screen.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tstipanic.factorynews_kotlin.R
import com.tstipanic.factorynews_kotlin.onboarding_screen.adapter.OnBoardingPagerAdapter
import kotlinx.android.synthetic.main.activity_on_boarding_pager.*

class OnBoardingActivity: AppCompatActivity(), OnBoardingView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
    }

    override fun initUi() {
        setContentView(R.layout.activity_on_boarding_pager)
        val onBoardingPagerFragment = OnBoardingPagerAdapter(supportFragmentManager)
        onBoardingViewPager.setBackgroundResource(android.R.color.transparent)
        onBoardingViewPager.adapter = onBoardingPagerFragment
        dotsIndicatiorOnboarding.setViewPager(onBoardingViewPager)
    }
}