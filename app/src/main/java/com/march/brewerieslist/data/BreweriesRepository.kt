package com.march.brewerieslist.data

import com.march.brewerieslist.data.local.BreweriesLocalDataSource
import com.march.brewerieslist.data.remote.BreweriesRemoteDataSource
import javax.inject.Inject

class BreweriesRepository @Inject constructor(
    val breweriesLocalDataSource: BreweriesLocalDataSource,
    val breweriesRemoteDataSource: BreweriesRemoteDataSource
) : BreweriesDataSource {

    override fun getBreweries(callback: BreweriesDataSource.LoadBreweriesCallback) {
//        breweriesLocalDataSource.getBreweries(callback)
        breweriesRemoteDataSource.getBreweries(callback)
    }
}