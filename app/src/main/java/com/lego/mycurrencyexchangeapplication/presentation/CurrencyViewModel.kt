package com.lego.mycurrencyexchangeapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lego.mycurrencyexchangeapplication.data.models.Resource
import com.lego.mycurrencyexchangeapplication.domain.CurrencyRepository
import com.lego.mycurrencyexchangeapplication.domain.models.Currency
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyViewModel(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    var isLoading = false
    val list = mutableListOf<Currency>() //

    fun update() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = currencyRepository.getLastUpdate()
            result.handleResponse(onSuccess = {
                list.addAll(it.currencyList.toMutableList())
                list
            })
        }
    }

    private fun <T> Resource<T>.handleResponse(
        onSuccess: (T) -> Unit,
        onError: (Resource.Failure) -> Unit = { //handle Error
        },
        onLoading: () -> Unit = { //handle Loading
        }
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            when (this@handleResponse) {
                is Resource.Failure -> onError(this@handleResponse)
                is Resource.Loading -> onLoading()
                is Resource.Success -> {
                    isLoading = false
                    onSuccess(this@handleResponse.value)
                }
            }
        }
    }
}
