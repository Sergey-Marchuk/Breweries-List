package com.march.brewerieslist.data

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Breweries")
data class Brewery(
    @SerializedName("brewery_type")
    val breweryType: String,
    @SerializedName("tag_list")
    val tagList: List<Any>,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("website_url")
    val websiteUrl: String,
    @SerializedName("postal_code")
    val postalCode: String,
    val id: Int,
    val city: String,
    val country: String,
    val latitude: String,
    val longitude: String,
    val name: String,
    val phone: String,
    val state: String,
    val street: String
)

typealias Breweries = List<Brewery>