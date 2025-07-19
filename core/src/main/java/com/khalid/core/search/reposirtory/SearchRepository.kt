package com.khalid.core.search.reposirtory

import com.khalid.core.search.model.SearchResponse
import com.khalid.core.utils.Response
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getSearchResult(searchValue: String): Flow<Response<SearchResponse>>
}