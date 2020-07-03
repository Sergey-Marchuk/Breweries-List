package com.march.brewerieslist.ui

import android.os.Bundle
import com.march.brewerieslist.R
import com.march.brewerieslist.ui.list.BreweriesListFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchBreweriesListScreen()
    }

    private fun launchBreweriesListScreen() {
        supportFragmentManager.beginTransaction()
            .add(android.R.id.content,
                BreweriesListFragment()
            )
            .commit()
    }
}