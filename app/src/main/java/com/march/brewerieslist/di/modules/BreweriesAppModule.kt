package com.march.brewerieslist.di.modules

import android.content.Context
import com.march.brewerieslist.App
import com.march.brewerieslist.di.modules.view_models.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module(
    includes = [ViewModelModule::class,
        ApiModule::class,
        DataSourceModule::class]
)
class BreweriesAppModule() {

    @Provides
    @Inject
    fun provideContext(context: App): Context {
        return context
    }
}