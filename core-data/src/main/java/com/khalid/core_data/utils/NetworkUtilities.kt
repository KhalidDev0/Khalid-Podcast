package com.khalid.core_data.utils

import com.khalid.core.utils.Response
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

internal object NetworkUtilities {
    fun <ConvertType> safeApiCall(
        apiCall: suspend () -> Response<ConvertType>
    ) = flow {
        emit(apiCall())
    }.catch {
        // This is just a quick solution but we can specify exactly the type of exception and handle it accordingly
        emit(Response.Error(errorCode = 4000, "Unknown error"))
    }
}