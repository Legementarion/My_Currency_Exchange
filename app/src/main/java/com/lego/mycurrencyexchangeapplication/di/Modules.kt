package com.lego.mycurrencyexchangeapplication.di

import com.lego.mycurrencyexchangeapplication.data.CurrencyApi
import com.lego.mycurrencyexchangeapplication.data.CurrencyRepositoryImpl
import com.lego.mycurrencyexchangeapplication.data.RetrofitFactory
import com.lego.mycurrencyexchangeapplication.domain.CurrencyRepository
import com.lego.mycurrencyexchangeapplication.presentation.CurrencyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<CurrencyRepository> { CurrencyRepositoryImpl(get()) }

    single { RetrofitFactory() }
    single<CurrencyApi> { get<RetrofitFactory>().getCurrencyApi() }

    viewModel { CurrencyViewModel(get()) }

}