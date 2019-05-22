package com.tstipanic.factorynews_kotlin.onboarding_screen.view.base

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tstipanic.factorynews_kotlin.R

abstract class BaseDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contextThemeWrapper = ContextThemeWrapper(activity, R.style.OnBoardingTheme)
        val localInflater = inflater.cloneInContext(contextThemeWrapper)
        return localInflater.inflate(getLayoutResourceId(), container, false)
        }

    abstract fun getLayoutResourceId(): Int
}