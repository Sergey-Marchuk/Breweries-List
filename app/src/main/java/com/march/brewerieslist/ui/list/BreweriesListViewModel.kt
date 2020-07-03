package com.march.brewerieslist.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.march.brewerieslist.data.Breweries
import com.march.brewerieslist.data.BreweriesDataSource
import com.march.brewerieslist.data.BreweriesRepository
import com.march.brewerieslist.data.Brewery
import javax.inject.Inject

class BreweriesListViewModel @Inject constructor() : ViewModel(),
    BreweriesDataSource.LoadBreweriesCallback {

    @Inject
    lateinit var breweriesRepository: BreweriesRepository
    var showProgress = MutableLiveData(false)
    var onUrlClickedCallback: ((String) -> Unit)? = null
    var onMapClickedCallback: ((Brewery) -> Unit)? = null
    var onErrorOccurredCallback: (() -> Unit)? = null

    val breweries = MutableLiveData<Breweries>()

    fun reloadBreweries() {
        initRepositoryCallback()
        showProgress.value = true
        breweriesRepository.getBreweries(this)
    }

    fun searchBreweries(query: String?) {
        showProgress.value = true
        breweriesRepository.searchBreweries(query, this)
    }

    override fun onBreweriesLoaded(breweries: List<Brewery>) {
        showProgress.value = false
        this.breweries.value = breweries
    }

    override fun onDataNotAvailable() {
        onErrorOccurredCallback?.invoke()
        showProgress.value = false
    }

    override fun onCleared() {
        breweriesRepository.clear()
        super.onCleared()
    }

    private fun initRepositoryCallback() {
        if (breweriesRepository.onSearchRequestGotCallback == null) {
            breweriesRepository.onSearchRequestGotCallback = {
                showProgress.value = false
            }
        }
    }
}