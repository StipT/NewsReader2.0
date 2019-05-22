package com.tstipanic.factorynews_kotlin.onboarding_screen.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.tstipanic.factorynews_kotlin.onboarding_screen.view.fragments.AboutAppFragment
import com.tstipanic.factorynews_kotlin.onboarding_screen.view.fragments.AboutMeFragment
import com.tstipanic.factorynews_kotlin.onboarding_screen.view.fragments.AboutMentorFragment

class OnBoardingPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment = when(p0) {
        0 -> AboutAppFragment.newInstance()
        1 -> AboutMeFragment.newInstance()
        2 -> AboutMentorFragment.newInstance()
        else -> AboutAppFragment.newInstance()
    }
    override fun getCount() = 3
}