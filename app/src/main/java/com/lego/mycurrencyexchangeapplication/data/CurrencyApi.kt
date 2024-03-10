package com.lego.mycurrencyexchangeapplication.data

import com.lego.mycurrencyexchangeapplication.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyApi {

    @GET("api/service/chernivtsi")
    suspend fun getLastUpdate(): Response<CurrencyResponse>

}