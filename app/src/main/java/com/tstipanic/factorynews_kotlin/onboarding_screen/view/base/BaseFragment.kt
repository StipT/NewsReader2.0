package com.tstipanic.factorynews_kotlin.onboarding_screen.view.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(getLayoutResourceId(), container, false)

    abstract fun getLayoutResourceId(): Int
}