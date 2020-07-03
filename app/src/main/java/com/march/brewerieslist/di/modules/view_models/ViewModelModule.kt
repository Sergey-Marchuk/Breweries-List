package com.march.brewerieslist.di.modules.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.march.brewerieslist.ui.list.BreweriesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BreweriesListViewModel::class)
    abstract fun bindBreweriesListViewModel(breweriesListViewModel: BreweriesListViewModel): ViewModel


    @Singleton
    @Binds
    abstract fun bindViewModelFactory(factory: BreweriesViewModelFactory): ViewModelProvider.Factory
}