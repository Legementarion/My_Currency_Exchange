package com.lego.mycurrencyexchangeapplication.data.models

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("serviceId") var serviceId: String? = null,
    @SerializedName("serviceName") var serviceName: String? = null,
    @SerializedName("exchangers") var exchangers: ArrayList<Exchangers> = arrayListOf()
)