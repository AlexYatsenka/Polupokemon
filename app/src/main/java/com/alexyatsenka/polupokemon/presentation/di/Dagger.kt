package com.alexyatsenka.polupokemon.presentation.di

import android.content.Context

object Dagger {
    private var mAppComponent : AppComponent? = null
    val appComponent get() = mAppComponent!!

    @JvmStatic
    fun buildComponent(context : Context) : AppComponent {
        if(mAppComponent == null) {
            mAppComponent = DaggerAppComponent.factory()
                .create(context)
        }
        return appComponent
    }
}