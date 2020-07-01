package com.march.brewerieslist.data.local

import com.march.brewerieslist.data.BreweriesDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class BreweriesLocalDataSource @Inject constructor(val breweriesDao: BreweriesDao) :
    BreweriesDataSource {

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

    fun clearDisposables() {
        disposables.clear()
    }
}