package com.khalid.core.utils

sealed class Response<T> {
    class Loading<T> : Response<T>()
    data class Success<T>(val data: T) : Response<T>()
    data class Error<T>(val errorCode: Int, val errorMessage: String) : Response<T>()
}
