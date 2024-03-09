package com.lego.mycurrencyexchangeapplication.domain

import com.lego.mycurrencyexchangeapplication.domain.models.Currencies

interface CurrencyRepository {

    fun getLastUpdate(): Result<Currencies>

}