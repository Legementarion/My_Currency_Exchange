package com.lego.mycurrencyexchangeapplication.data

import com.lego.mycurrencyexchangeapplication.data.models.CurrencyResponse
import com.lego.mycurrencyexchangeapplication.data.models.Rates
import com.lego.mycurrencyexchangeapplication.domain.models.Currencies
import com.lego.mycurrencyexchangeapplication.domain.models.Currency

fun CurrencyResponse.toDomain(): Currencies {
    return Currencies(
        this.exchangers.first().rates?.toDomain() ?: emptyList(),
    )
}

fun Rates.toDomain(): List<Currency> {
    val rateList = mutableListOf<Currency>()

    usd?.let { rateList.add(Currency("usd", it.sell, it.buy)) }
    eur?.let { rateList.add(Currency("eur", it.sell, it.buy)) }
    rur?.let { rateList.add(Currency("rur", it.sell, it.buy)) }
    gbp?.let { rateList.add(Currency("gbp", it.sell, it.buy)) }
    chf?.let { rateList.add(Currency("chf", it.sell, it.buy)) }
    pln?.let { rateList.add(Currency("pln", it.sell, it.buy)) }

    return rateList
}

