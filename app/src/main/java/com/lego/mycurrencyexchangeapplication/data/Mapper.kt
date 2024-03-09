package com.lego.mycurrencyexchangeapplication.data

import com.lego.mycurrencyexchangeapplication.data.models.Exchangers
import com.lego.mycurrencyexchangeapplication.data.models.Response
import com.lego.mycurrencyexchangeapplication.domain.models.Currencies
import com.lego.mycurrencyexchangeapplication.domain.models.Currency

fun Response.toDomain(): Currencies {
    return Currencies(
        this.exchangers.toDomain(),
    )
}

fun List<Exchangers>.toDomain(): List<Currency> {
    return this.map {
        Currency(
            it.name ?: "",
            it.rates?.usd?.sell ?: "",
            it.rates?.usd?.buy ?: ""
        )
    }
}

