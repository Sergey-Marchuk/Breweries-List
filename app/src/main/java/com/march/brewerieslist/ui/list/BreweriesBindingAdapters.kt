package com.march.brewerieslist.ui.list

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.button.MaterialButton
import com.march.brewerieslist.R
import com.march.brewerieslist.data.Breweries


object BreweriesBindingAdapters {

    @JvmStatic
    @BindingAdapter("app:breweries")
    fun RecyclerView.setBreweries(breweries: Breweries?) {
        breweries?.let { (adapter as BreweriesAdapter).setBreweries(breweries) }
    }

    @JvmStatic
    @BindingAdapter("app:breweryInfoText")
    fun TextView.setBreweryInfoText(text: String) {
        val spannableString = SpannableString(text)
        val dotsIndex = spannableString.indexOf(':')
        val infoTextColor = ContextCompat.getColor(context, R.color.color_text_light_grey)
        val valueTextColor = ContextCompat.getColor(context, R.color.color_dark_grey)
        spannableString.setSpan(
            ForegroundColorSpan(infoTextColor), 0,
            dotsIndex + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            ForegroundColorSpan(valueTextColor), dotsIndex + 2,
            spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        if (id == R.id.websiteTV) {
            spannableString.setSpan(
                UnderlineSpan(), dotsIndex + 2,
                spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        if (text.substring(dotsIndex, text.length).length > 2) {
            isVisible = true
            setText(spannableString)
        } else {
            isVisible = false
        }
    }

    @JvmStatic
    @BindingAdapter("app:isVisible")
    fun View.setIsVisible(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("app:latitude", "app:longitude", requireAll = true)
    fun MaterialButton.setVisibility(latitude: String?, longitude: String?) {
        val digitLatitude = latitude?.toFloatOrNull()
        val digitLongitude = longitude?.toFloatOrNull()
        isVisible = digitLatitude != null && digitLongitude != null
    }

    @JvmStatic
    @BindingAdapter("app:showProgress")
    fun SwipeRefreshLayout.setProgress(isInProgress: Boolean) {
        if (isRefreshing != isInProgress) {
            isRefreshing = isInProgress
        }
    }
}