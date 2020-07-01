package com.march.brewerieslist.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.march.brewerieslist.BaseFragment
import com.march.brewerieslist.R
import com.march.brewerieslist.api.BreweriesApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class BreweriesListFragment: BaseFragment() {

    @Inject
    lateinit var breweriesApiService: BreweriesApiService
    lateinit var viewModel: BreweriesListViewModel

    override val layoutRes: Int =
        R.layout.fragment_breweries_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        subscribeOnBreweries()
        viewModel.reloadBreweries()
    }

    private fun subscribeOnBreweries() {
        viewModel.breweries.observe(viewLifecycleOwner, Observer {
            Timber.e("breweries: $it")
        })
    }
}