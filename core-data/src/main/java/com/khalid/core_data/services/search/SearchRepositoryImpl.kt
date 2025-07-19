package com.khalid.core_data.services.search

import com.khalid.core.search.model.SearchResponse
import com.khalid.core.search.reposirtory.SearchRepository
import com.khalid.core.utils.Response
import com.khalid.core_data.services.search.dto.toDomain
import com.khalid.core_data.utils.NetworkUtilities
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi,
) : SearchRepository {
    override suspend fun getSearchResult(searchValue: String): Flow<Response<SearchResponse>> =
        NetworkUtilities.safeApiCall {
            Response.Success(searchApi.search(searchValue).toDomain())
        }
}