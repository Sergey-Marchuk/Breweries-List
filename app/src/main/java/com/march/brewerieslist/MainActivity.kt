package com.march.brewerieslist

import android.os.Bundle
import com.march.brewerieslist.ui.BreweriesListFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchBreweriesListScreen()
    }

    private fun launchBreweriesListScreen() {
        supportFragmentManager.beginTransaction()
            .add(android.R.id.content, BreweriesListFragment())
            .commit()
    }
}