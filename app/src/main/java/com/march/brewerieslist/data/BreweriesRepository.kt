package com.march.brewerieslist.data

import com.march.brewerieslist.data.local.BreweriesLocalDataSource
import com.march.brewerieslist.data.remote.BreweriesRemoteDataSource
import javax.inject.Inject

class BreweriesRepository @Inject constructor(
    private val breweriesLocalDataSource: BreweriesLocalDataSource,
    private val breweriesRemoteDataSource: BreweriesRemoteDataSource
) : BreweriesDataSource {

    var onSearchRequestGotCallback: (() -> Unit)? = null

    override fun getBreweries(callback: BreweriesDataSource.LoadBreweriesCallback) {
        breweriesLocalDataSource.getBreweries(callback)
        breweriesRemoteDataSource.getBreweries(object: BreweriesDataSource.LoadBreweriesCallback {
            override fun onBreweriesLoaded(breweries: List<Brewery>) {
                breweriesLocalDataSource.insertBreweries(breweries)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

    fun searchBreweries(query: String?, callback: BreweriesDataSource.LoadBreweriesCallback) {
        breweriesRemoteDataSource.searchBreweries(query, object: BreweriesDataSource.LoadBreweriesCallback {
            override fun onBreweriesLoaded(breweries: List<Brewery>) {
                breweriesLocalDataSource.insertBreweries(breweries)
                onSearchRequestGotCallback?.invoke()
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
                onSearchRequestGotCallback?.invoke()
            }
        })
    }

    fun clear() {
        breweriesRemoteDataSource.clearDisposables()
        breweriesLocalDataSource.clearDisposables()
    }
}