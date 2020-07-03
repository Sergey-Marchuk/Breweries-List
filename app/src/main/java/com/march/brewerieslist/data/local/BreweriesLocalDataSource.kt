package com.march.brewerieslist.data.local

import com.march.brewerieslist.data.Breweries
import com.march.brewerieslist.data.BreweriesDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class BreweriesLocalDataSource
@Inject constructor(private val breweriesDao: BreweriesDao) : BreweriesDataSource {

    private val disposables = CompositeDisposable()

    override fun getBreweries(callback: BreweriesDataSource.LoadBreweriesCallback) {
        disposables.add(
            breweriesDao.getBreweries().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onBreweriesLoaded(it)
                }, {
                    callback.onDataNotAvailable()
                    Timber.w(it)
                })
        )
    }

    fun insertBreweries(breweries: Breweries) {
        disposables.add(Observable.fromCallable {
            breweriesDao.clearBreweries()
            breweriesDao.insertBreweries(breweries)
        }.subscribeOn(Schedulers.io())
            .subscribe({}, Timber::w))
    }

    fun clearDisposables() {
        disposables.clear()
    }
}