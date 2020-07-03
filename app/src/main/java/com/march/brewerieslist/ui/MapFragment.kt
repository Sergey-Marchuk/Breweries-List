package com.march.brewerieslist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.march.brewerieslist.R

class MapFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        arguments?.apply {
            val latitude = getDouble(KEY_LATITUDE)
            val longitude = getDouble(KEY_LONGITUDE)
            val breweryName = getString(KEY_BREWERY_NAME)
            val location = LatLng(latitude, longitude)
            googleMap.addMarker(MarkerOptions().position(location).title(breweryName))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, ZOOM_LEVEL))
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    companion object {
        private const val KEY_LATITUDE = "key_latitude"
        private const val KEY_LONGITUDE = "key_longitude"
        private const val KEY_BREWERY_NAME = "key_brewery_name"
        private const val ZOOM_LEVEL = 20f

        fun newInstance(latitude: Double, longitude: Double, breweryName: String): MapFragment {
            val args = Bundle()
            args.putDouble(KEY_LATITUDE, latitude)
            args.putDouble(KEY_LONGITUDE, longitude)
            args.putString(KEY_BREWERY_NAME, breweryName)
            val fragment = MapFragment()
            fragment.arguments = args
            return fragment
        }
    }
}