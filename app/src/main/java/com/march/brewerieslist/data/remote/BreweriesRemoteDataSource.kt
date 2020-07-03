package com.march.brewerieslist.data.remote

import com.march.brewerieslist.api.BreweriesApiService
import com.march.brewerieslist.data.BreweriesDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class BreweriesRemoteDataSource
@Inject constructor(private val breweriesApiService: BreweriesApiService) : BreweriesDataSource {

    private val disposables = CompositeDisposable()
    private val publishSubject =
        PublishSubject.create<Pair<String?, BreweriesDataSource.LoadBreweriesCallback>>()

    init {
        disposables.add(
            publishSubject
                .debounce(REQUEST_DELAY_MILLS, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ sendSearchRequest(it) }, Timber::w))
    }

    override fun getBreweries(callback: BreweriesDataSource.LoadBreweriesCallback) {
        disposables.add(breweriesApiService.getBreweries().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onBreweriesLoaded(it)
            }, {
                callback.onDataNotAvailable()
                Timber.w(it)
            })
        )
    }

    fun searchBreweries(query: String?, callback: BreweriesDataSource.LoadBreweriesCallback) {
        publishSubject.onNext(Pair(query, callback))
    }

    fun clearDisposables() {
        disposables.clear()
    }

    private fun sendSearchRequest(pair: Pair<String?, BreweriesDataSource.LoadBreweriesCallback>) {
        val query = pair.first
        if (pair.first.isNullOrEmpty()) {
            getBreweries(pair.second)
        } else if (query != null) {
            breweriesApiService.searchBreweries(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    pair.second.onBreweriesLoaded(it)
                }, {
                    pair.second.onDataNotAvailable()
                    Timber.w(it)
                })
        }
    }

    companion object {
        private const val REQUEST_DELAY_MILLS = 300L
    }

}