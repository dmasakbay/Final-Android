package com.zet.coronavirusstatistics.utils

sealed interface ApiResult<T : Any>

class ApiSuccess<T : Any>(val data: T) : ApiResult<T>
class ApiError<T : Any>(val code: Int, val message: String?) : ApiResult<T>
class ApiException<T : Any>(val e: Throwable) : ApiResult<T>

fun <T : Any> ApiResult<T>?.string(): String {
    return when (this) {
        is ApiError -> "ApiError[error=$message]"
        is ApiException -> "Error[exception=${e.message}]"
        is ApiSuccess -> "Success[data=$data]"
        else -> "This result is null"
    }
}