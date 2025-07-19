package com.khalid.search.presentation

import com.khalid.core_ui.common.listing.twoLineGrid.TwoLineGridCardUiModel

data class SearchState(
    val searchValue: String = "",
    val isSearching: Boolean = false,
    val twoLineGridCardUiModels: List<TwoLineGridCardUiModel> = emptyList(),
)
