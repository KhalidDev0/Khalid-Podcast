package com.khalid.core.search.model

data class SearchSection(
    val name: String? = null,
    val content: List<SearchContent?>? = null,
    val order: String? = null
)