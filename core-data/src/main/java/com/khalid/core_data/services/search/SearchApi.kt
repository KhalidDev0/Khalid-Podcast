package com.khalid.core_data.services.search

import com.khalid.core_data.services.search.dto.SearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("m1/735111-711675-default/search")
    suspend fun search(
        @Query("value") searchValue: String,
    ): SearchResponseDto
}