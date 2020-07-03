package com.march.brewerieslist.ui

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.march.brewerieslist.R
import timber.log.Timber

class MapFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        Timber.e("onMapReady")
        arguments?.apply {
            val latitude = getDouble(KEY_LATITUDE)
            val longitude = getDouble(KEY_LONGITUDE)
            val breweryName = getString(KEY_BREWERY_NAME)
            val location = LatLng(latitude, longitude)
            googleMap.addMarker(MarkerOptions().position(location).title(breweryName))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
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