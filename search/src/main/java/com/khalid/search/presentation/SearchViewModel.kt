package com.khalid.search.presentation

import androidx.lifecycle.viewModelScope
import com.khalid.core.search.usecase.GetSearchResultUseCase
import com.khalid.core.utils.update
import com.khalid.core_ui.bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase,
) : BaseViewModel<SearchState, SearchEvent>() {
    override val privateState = MutableStateFlow(SearchState())
    private val searchValueHandler = MutableStateFlow("")

    init {
        searchValueHandler
            .debounce(200L)
            .distinctUntilChanged()
            .filter { it.isNotBlank() }
            .onEach(::search)
            .launchIn(viewModelScope)
    }

    override fun onEvent(event: SearchEvent) {
        super.onEvent(event)
        when (event) {
            is SearchEvent.OnTextChanged -> {
                searchValueHandler.value = event.newValue
                privateState.update {
                    copy(searchValue = event.newValue)
                }
            }
        }
    }

    private fun search(value: String) {
        onUseCaseUse(
            useCase = { getSearchResultUseCase(value) },
            onLoading = {
                privateState.update {
                    copy(isSearching = it)
                }
            },
            onSuccess = {
                privateState.update {
                    copy(searchResult = it)
                }
            },
        )
    }
}