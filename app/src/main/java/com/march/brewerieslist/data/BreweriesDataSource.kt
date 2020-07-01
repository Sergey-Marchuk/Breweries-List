package com.march.brewerieslist.data

interface BreweriesDataSource {

    interface LoadBreweriesCallback {

        fun onBreweriesLoaded(breweries: List<Brewery>)

        fun onDataNotAvailable()
    }

    fun getBreweries(callback: LoadBreweriesCallback)
}