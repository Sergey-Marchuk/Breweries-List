package com.march.brewerieslist.di.modules

import com.march.brewerieslist.ui.BreweriesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeRegistrationFragment(): BreweriesListFragment
}