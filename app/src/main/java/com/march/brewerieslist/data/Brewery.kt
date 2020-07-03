package com.march.brewerieslist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Breweries")
data class Brewery(
    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0,
    @SerializedName("brewery_type")
    var breweryType: String? = "",
    @SerializedName("updated_at")
    var updatedAt: String? = "",
    @SerializedName("website_url")
    var websiteUrl: String? = "",
    @SerializedName("postal_code")
    var postalCode: String? = "",
    @SerializedName("id")
    var serverId: Int = -1,
    var city: String? = "",
    var country: String = "",
    var latitude: String? = "",
    var longitude: String? = "",
    var name: String? = "",
    var phone: String? = "",
    var state: String? = "",
    var street: String? = ""
)

typealias Breweries = List<Brewery>