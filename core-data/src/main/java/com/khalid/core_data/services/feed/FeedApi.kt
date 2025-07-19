package com.khalid.core_data.services.feed

import com.khalid.core_data.services.feed.dto.FeedResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

internal interface FeedApi {

    @GET("home_sections")
    suspend fun getHomeSections(
        @Query("page") page: Int = 1
    ): FeedResponseDto
}