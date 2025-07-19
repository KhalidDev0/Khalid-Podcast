package com.khalid.core_data.services.feed

import com.khalid.core.feed.model.Feed
import com.khalid.core.feed.repository.FeedRepository
import com.khalid.core.utils.Response
import com.khalid.core_data.services.feed.mappers.FeedMapper
import com.khalid.core_data.utils.NetworkUtilities
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class FeedRepositoryImpl @Inject constructor(
    private val feedApi: FeedApi,
    private val feedMapper: FeedMapper
) : FeedRepository {
    override suspend fun getHomeSections(page: Int): Flow<Response<Feed>> =
        NetworkUtilities.safeApiCall {
            Response.Success(feedMapper.map(feedApi.getHomeSections(page)))
        }
}