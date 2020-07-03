package com.march.brewerieslist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.march.brewerieslist.data.Breweries
import io.reactivex.Flowable

@Dao
interface BreweriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBreweries(breweries: Breweries)

    @Query("delete from Breweries")
    fun clearBreweries()

    @Query("select * from Breweries")
    fun getBreweries(): Flowable<Breweries>

}