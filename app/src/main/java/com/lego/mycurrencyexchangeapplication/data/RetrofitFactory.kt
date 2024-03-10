package com.lego.mycurrencyexchangeapplication.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory() {

    companion object {
        const val BASE_URL = "https://rate.in.ua"
    }

    fun getCurrencyApi(): CurrencyApi {
        return buildApi(CurrencyApi::class.java)
    }

    private fun <Api> buildApi(
        api: Class<Api>,
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}