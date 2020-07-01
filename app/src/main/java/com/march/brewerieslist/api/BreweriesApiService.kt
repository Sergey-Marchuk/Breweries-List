package com.march.brewerieslist.api

import com.march.brewerieslist.data.Breweries
import io.reactivex.Observable
import retrofit2.http.GET

interface BreweriesApiService {

    @GET("breweries/")
    fun getBreweries(): Observable<Breweries>

}