package com.lego.mycurrencyexchangeapplication.data.models

import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("buy") var buy: String? = null,
    @SerializedName("sel") var sell: String? = null
)