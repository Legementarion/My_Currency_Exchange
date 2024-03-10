package com.lego.mycurrencyexchangeapplication.domain

import com.lego.mycurrencyexchangeapplication.data.models.Resource
import com.lego.mycurrencyexchangeapplication.domain.models.Currencies

interface CurrencyRepository {

    suspend fun getLastUpdate(): Resource<Currencies>

}