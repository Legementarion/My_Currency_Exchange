package com.lego.mycurrencyexchangeapplication.data

import com.lego.mycurrencyexchangeapplication.data.models.Response
import retrofit2.http.GET

interface CurrencyApi {

    @GET("api/service/chernivtsi")
    fun getLastUpdate(): Response

}