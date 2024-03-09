package com.lego.mycurrencyexchangeapplication.data.models

import com.google.gson.annotations.SerializedName

data class Exchangers(
    @SerializedName("id") var id: String? = null,
    @SerializedName("serviceId") var serviceId: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("website") var website: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("background") var background: String? = null,
    @SerializedName("border") var border: String? = null,
    @SerializedName("slug") var slug: String? = null,
    @SerializedName("rates") var rates: Rates? = Rates()
)