package com.march.brewerieslist.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.march.brewerieslist.data.Breweries
import com.march.brewerieslist.data.BreweriesDataSource
import com.march.brewerieslist.data.BreweriesRepository
import com.march.brewerieslist.data.Brewery
import javax.inject.Inject

class BreweriesListViewModel @Inject constructor(): ViewModel(), BreweriesDataSource.LoadBreweriesCallback {

    @Inject
    lateinit var breweriesRepository: BreweriesRepository

    val breweries = MutableLiveData<Breweries>()

    fun reloadBreweries() {
        breweriesRepository.getBreweries(this)
    }

    override fun onBreweriesLoaded(breweries: List<Brewery>) {
        this.breweries.value = breweries
    }

    override fun onDataNotAvailable() {
        TODO("Not yet implemented")
    }
}