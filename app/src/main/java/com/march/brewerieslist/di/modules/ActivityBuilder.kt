package com.march.brewerieslist.di.modules

import com.march.brewerieslist.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeAppActivity(): MainActivity
}