package com.march.brewerieslist

import com.march.brewerieslist.di.DaggerBreweriesListComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class App @Inject constructor(): DaggerApplication() {

    companion object {
        lateinit var INSTANCE: App
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerBreweriesListComponent.builder().application(this).build()
    }

}