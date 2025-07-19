package com.khalid.core_ui.bases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khalid.core.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseViewModel<State, Event> : ViewModel() {
    protected abstract val privateState: MutableStateFlow<State>
    val state get() = privateState.asStateFlow()
    open fun onEvent(event: Event) {}
    protected fun <UseCaseReturnType> onUseCaseUse(
        useCase: () -> Flow<Response<UseCaseReturnType>>,
        onLoading: (status: Boolean) -> Unit = {},
        onSuccess: (UseCaseReturnType) -> Unit = {},
        onError: (errorCode: Int, errorMessage: String) -> Unit = { _, _ -> },
    ) {
        useCase().onEach { response ->
            when (response) {
                is Response.Loading -> onLoading(true)
                is Response.Success -> {
                    onLoading(false)
                    onSuccess(response.data)
                }

                is Response.Error -> {
                    onLoading(false)
                    onError(response.errorCode, response.errorMessage)
                }
            }
        }.launchIn(viewModelScope)
    }
}