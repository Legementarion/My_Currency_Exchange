package com.lego.mycurrencyexchangeapplication.data

import com.lego.mycurrencyexchangeapplication.data.models.Resource
import com.lego.mycurrencyexchangeapplication.domain.CurrencyRepository
import com.lego.mycurrencyexchangeapplication.domain.models.Currencies

class CurrencyRepositoryImpl(private val service: CurrencyApi) : CurrencyRepository, SafeApiCall  {

    override suspend fun getLastUpdate(): Resource<Currencies> {
        val result = safeApiCall {
            service.getLastUpdate()
        }
        return resultHandler(result, onSuccess = {
            it.value.toDomain()
        })
    }

}