package com.lego.mycurrencyexchangeapplication.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lego.mycurrencyexchangeapplication.data.models.ErrorResponse
import com.lego.mycurrencyexchangeapplication.data.models.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke().body()!!)
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Failure(true, throwable.code(), throwable.response()?.errorBody().toString())
                    }
                    is IOException -> {
                        Resource.Failure(true, 0, throwable.message)
                    }
                    else -> {
                        val gson = Gson()
                        val type = object : TypeToken<ErrorResponse>() {}.type
                        val errorResponse: ErrorResponse? = gson.fromJson(apiCall.invoke().errorBody()?.charStream()?.readText(), type)
                        Resource.Failure(
                            true,
                            errorResponse?.status,
                            errorMessage = errorResponse?.errorDescription
                        )
                    }
                }
            }
        }
    }
}

inline fun <T, K> resultHandler(
    result: Resource<T>,
    onSuccess: (result: Resource.Success<T>) -> K
): Resource<K> {
    return when (result) {
        is Resource.Failure -> {
            result
        }
        is Resource.Loading -> {
            result
        }
        else -> {
            Resource.Success(onSuccess(result as Resource.Success))
        }
    }
}
