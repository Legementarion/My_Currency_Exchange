package com.lego.mycurrencyexchangeapplication.data.models

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorMessage: String?
    ) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}

data class ErrorResponse(
    val status: Int,
    val data: List<Any>,
    val errorDescription: String?
)
