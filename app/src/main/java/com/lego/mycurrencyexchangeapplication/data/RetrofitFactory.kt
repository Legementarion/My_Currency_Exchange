package com.lego.mycurrencyexchangeapplication.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private lateinit var retrofit: Retrofit

    fun createClient(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl("https://rate.in.ua/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }


    var service: CurrencyApi = retrofit.create(CurrencyApi::class.java)
}