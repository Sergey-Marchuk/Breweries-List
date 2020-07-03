package com.march.brewerieslist.di.modules

import android.content.Context
import com.march.brewerieslist.App
import com.march.brewerieslist.di.modules.view_models.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BreweriesAppModule {

    @Singleton
    @Provides
    fun provideContext(application: App): Context {
        return application
    }
}