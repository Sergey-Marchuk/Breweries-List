package com.march.brewerieslist.di.modules

import androidx.room.Room
import com.march.brewerieslist.App
import com.march.brewerieslist.api.BreweriesApiService
import com.march.brewerieslist.data.BreweriesRepository
import com.march.brewerieslist.data.local.BreweriesDao
import com.march.brewerieslist.data.local.BreweriesDatabase
import com.march.brewerieslist.data.local.BreweriesLocalDataSource
import com.march.brewerieslist.data.remote.BreweriesRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class DataSourceModule {

    private var TABLE_NAME = "Breweries.db"

    @Provides
    fun provideBreweriesLocalDataSource(breweriesDao: BreweriesDao):
            BreweriesLocalDataSource {
        return BreweriesLocalDataSource(breweriesDao)
    }

    @Provides
    fun provideBreweriesRemoteDataSource(apiService: BreweriesApiService):
            BreweriesRemoteDataSource {
        return BreweriesRemoteDataSource(apiService)
    }

    //TODO: Need fix providing of context
    @Provides
    fun provideBreweriesDatabase(): BreweriesDatabase {
        return Room.databaseBuilder(App.INSTANCE, BreweriesDatabase::class.java, TABLE_NAME)
            .build()
    }

    @Provides
    fun providesBreweriesDao(database: BreweriesDatabase): BreweriesDao {
        return database.breweriesDao()
    }

    @Provides
    fun provideBreweriesRepository(
        localDataSource: BreweriesLocalDataSource,
        remoteDataSource: BreweriesRemoteDataSource
    ): BreweriesRepository {
        return BreweriesRepository(localDataSource, remoteDataSource)
    }
}