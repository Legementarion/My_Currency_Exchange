package com.lego.mycurrencyexchangeapplication.data.models

import com.google.gson.annotations.SerializedName

data class Rates(
    @SerializedName("exchangerId") var exchangerId: String? = null,
    @SerializedName("updateTime") var updateTime: Int? = null,
    @SerializedName("usd") var usd: Currency? = null,
    @SerializedName("eur") var eur: Currency? = null,
    @SerializedName("rur") var rur: Currency? = null,
    @SerializedName("gbp") var gbp: Currency? = null,
    @SerializedName("chf") var chf: Currency? = null,
    @SerializedName("pln") var pln: Currency? = null
)