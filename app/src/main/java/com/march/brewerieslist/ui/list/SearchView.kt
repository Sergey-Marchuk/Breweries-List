package com.march.brewerieslist.ui.list

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.march.brewerieslist.R
import kotlinx.android.synthetic.main.view_search.view.*

class SearchView : ConstraintLayout {

    val editText: EditText
        get() = searchET

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        inflate(context, R.layout.view_search, this)
        setBackgroundResource(R.color.color_light_green)
        val padding  = resources.getDimensionPixelSize(R.dimen.breweries_list_search_padding)
        setPadding(padding, padding, padding, padding)
        searchET.doAfterTextChanged {
            hintTV.isVisible = it.isNullOrBlank()
        }
    }
}