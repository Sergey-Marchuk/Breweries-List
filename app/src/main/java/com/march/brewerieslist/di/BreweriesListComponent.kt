package com.march.brewerieslist.di

import android.app.Application
import com.march.brewerieslist.App
import com.march.brewerieslist.di.modules.ActivityBuilder
import com.march.brewerieslist.di.modules.BreweriesAppModule
import com.march.brewerieslist.di.modules.DataSourceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        BreweriesAppModule::class,
        ActivityBuilder::class]
)
interface BreweriesListComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): BreweriesListComponent
    }

}