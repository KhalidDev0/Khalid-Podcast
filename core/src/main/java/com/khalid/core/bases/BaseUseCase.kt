package com.khalid.core.bases

import com.khalid.core.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

open class BaseUseCase {
    protected fun <ReturnType> initUseCase(
        repositoryToCall: suspend () -> Flow<Response<ReturnType>>
    ) = flow {
        emit(Response.Loading())
        emitAll(repositoryToCall())
    }
}