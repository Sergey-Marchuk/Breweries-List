package com.march.brewerieslist.api

import com.march.brewerieslist.data.Breweries
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface BreweriesApiService {

    @GET("breweries/")
    fun getBreweries(): Observable<Breweries>

    @GET("breweries/search")
    fun searchBreweries(@Query("query") query: String): Observable<Breweries>

}