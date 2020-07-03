package com.march.brewerieslist.di.modules

import com.march.brewerieslist.ui.list.BreweriesListFragment
import com.march.brewerieslist.ui.web.WebViewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeBreweriesListFragment(): BreweriesListFragment

    @ContributesAndroidInjector
    abstract fun contributeWebViewFragment(): WebViewFragment
}