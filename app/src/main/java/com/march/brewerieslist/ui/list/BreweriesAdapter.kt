package com.march.brewerieslist.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.march.brewerieslist.data.Breweries
import com.march.brewerieslist.data.Brewery
import com.march.brewerieslist.databinding.ItemBreweryBinding
import kotlinx.android.synthetic.main.item_brewery.view.*

class BreweriesAdapter(
    private val viewModel: BreweriesListViewModel
) : RecyclerView.Adapter<BreweryViewHolder>() {

    val breweries: ArrayList<Brewery> = ArrayList()
    private val diffUtils = BreweriesDiffUtils()

    fun setBreweries(breweries: Breweries) {
        diffUtils.updateItems(this.breweries, breweries)
        this.breweries.clear()
        this.breweries.addAll(breweries)
        val diffResult = DiffUtil.calculateDiff(diffUtils)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreweryViewHolder {
        val binding = ItemBreweryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreweryViewHolder(
            viewModel,
            binding,
            this
        )
    }

    override fun onBindViewHolder(holder: BreweryViewHolder, position: Int) {
        val brewery = breweries[position]
        holder.bindBrewery(brewery)
    }

    override fun getItemCount(): Int = breweries.size

}

class BreweryViewHolder(
    private val viewModel: BreweriesListViewModel,
    private val binding: ItemBreweryBinding,
    private val adapter: BreweriesAdapter
) : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.websiteTV.setOnClickListener {
            val brewery = adapter.breweries.getItemByPosition(adapterPosition)
            brewery?.websiteUrl?.let { viewModel.onUrlClickedCallback?.invoke(it) }
        }
    }

    fun bindBrewery(brewery: Brewery) {
        with(binding) {
            this.brewery = brewery
        }
    }
}

class BreweriesDiffUtils : DiffUtil.Callback() {
    var oldItems = ArrayList<Brewery>()
    var newItems = ArrayList<Brewery>()

    fun updateItems(oldItems: Breweries, newItems: Breweries) {
        this.oldItems.clear()
        this.oldItems.addAll(oldItems)
        this.newItems.clear()
        this.newItems.addAll(newItems)
    }

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems.getItemByPosition(oldItemPosition)
        val newItem = newItems.getItemByPosition(newItemPosition)
        return oldItem?.serverId == newItem?.serverId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems.getItemByPosition(oldItemPosition)
        val newItem = newItems.getItemByPosition(newItemPosition)
        return oldItem != null && newItem != null &&
                oldItem.name == newItem.name &&
                oldItem.city == newItem.city &&
                oldItem.country == newItem.country &&
                oldItem.state == newItem.state &&
                oldItem.street == newItem.street &&
                oldItem.latitude == newItem.latitude &&
                oldItem.longitude == newItem.longitude &&
                oldItem.websiteUrl == newItem.websiteUrl
    }

}

private fun ArrayList<Brewery>.getItemByPosition(position: Int): Brewery? {
    var brewery: Brewery? = null
    if (position in 0 until size) {
        brewery = get(position)
    }

    return brewery
}