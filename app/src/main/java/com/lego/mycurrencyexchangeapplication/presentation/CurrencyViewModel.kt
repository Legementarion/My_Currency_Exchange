package com.lego.mycurrencyexchangeapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lego.mycurrencyexchangeapplication.domain.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyViewModel(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    fun update() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = currencyRepository.getLastUpdate()
        }
    }
}
