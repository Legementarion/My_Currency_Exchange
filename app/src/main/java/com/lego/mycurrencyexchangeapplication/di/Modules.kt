package com.lego.mycurrencyexchangeapplication.di

import com.lego.mycurrencyexchangeapplication.data.CurrencyApi
import com.lego.mycurrencyexchangeapplication.data.CurrencyRepositoryImpl
import com.lego.mycurrencyexchangeapplication.data.RetrofitFactory
import com.lego.mycurrencyexchangeapplication.domain.CurrencyRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single<CurrencyRepository> { CurrencyRepositoryImpl(get()) }
    single<Retrofit>{ RetrofitFactory().createClient() }
    single<CurrencyApi> { RetrofitFactory().service }

}