package com.march.brewerieslist.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.march.brewerieslist.R
import com.march.brewerieslist.data.Brewery
import com.march.brewerieslist.databinding.FragmentBreweriesListBinding
import com.march.brewerieslist.ui.BaseFragment
import com.march.brewerieslist.ui.MapFragment
import com.march.brewerieslist.ui.web.WebViewFragment
import kotlinx.android.synthetic.main.fragment_breweries_list.*

class BreweriesListFragment : BaseFragment() {

    lateinit var viewModel: BreweriesListViewModel
    lateinit var viewDataBinding: FragmentBreweriesListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentBreweriesListBinding
            .inflate(inflater, container, false)
        viewModel = injectViewModel(viewModelFactory)
        viewDataBinding.viewModel = viewModel
        viewDataBinding.lifecycleOwner = this
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initViews()
        subscribeOnCallbacks()
        viewModel.reloadBreweries()
    }

    private fun subscribeOnCallbacks() {
        viewModel.onUrlClickedCallback = {
            navigateToWebViewScreen(it)
        }

        viewModel.onMapClickedCallback = {
            navigateToMapScreen(it)
        }

        viewModel.onErrorOccurredCallback = {
            Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_LONG).show()
        }
    }

    private fun navigateToMapScreen(brewery: Brewery) {
        val latitude = brewery.latitude?.toDoubleOrNull() ?: return
        val longitude = brewery.longitude?.toDoubleOrNull() ?: return
        val breweryName = brewery.name ?: ""
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.addToBackStack(null)
            ?.replace(
                android.R.id.content,
                MapFragment.newInstance(latitude, longitude, breweryName)
            )
            ?.commit()
    }

    private fun navigateToWebViewScreen(url: String) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.addToBackStack(null)
            ?.replace(android.R.id.content, WebViewFragment.newInstance(url))
            ?.commit()
    }

    private fun initList() {
        breweriesRV.adapter = BreweriesAdapter(viewModel)
        breweriesRV.layoutManager = LinearLayoutManager(context)
    }

    private fun initViews() {
        searchSV.editText.doAfterTextChanged {
            viewModel.searchBreweries(it?.toString())
        }

        refreshSRL.setOnRefreshListener {
            viewModel.reloadBreweries()
        }
    }
}