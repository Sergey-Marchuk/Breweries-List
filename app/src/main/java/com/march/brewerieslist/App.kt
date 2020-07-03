package com.march.brewerieslist

import com.march.brewerieslist.di.DaggerBreweriesListComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class App : DaggerApplication() {

    companion object {
        lateinit var INSTANCE: App
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        Timber.plant(Timber.DebugTree());
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerBreweriesListComponent.builder().application(this).build()
    }

}