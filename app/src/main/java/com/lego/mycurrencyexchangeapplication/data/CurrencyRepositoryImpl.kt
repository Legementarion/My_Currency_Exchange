package com.lego.mycurrencyexchangeapplication.data

import com.lego.mycurrencyexchangeapplication.domain.CurrencyRepository
import com.lego.mycurrencyexchangeapplication.domain.models.Currencies

class CurrencyRepositoryImpl(private val service: CurrencyApi) : CurrencyRepository {

    override fun getLastUpdate(): Result<Currencies> {
        val result = service.getLastUpdate()
        if (result.serviceId != null) {
            return Result.success(service.getLastUpdate().toDomain())
        }
        return Result.failure(Exception("Cannot open HttpURLConnection"))
    }

}