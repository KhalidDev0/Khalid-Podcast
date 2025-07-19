package com.khalid.search.presentation

import com.khalid.core.search.model.SearchResponse

data class SearchState(
    val searchValue: String = "",
    val isSearching: Boolean = false,
    val searchResult: SearchResponse? = null,
)
