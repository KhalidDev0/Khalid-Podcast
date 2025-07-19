package com.khalid.core.feed.repository

import com.khalid.core.bases.BaseRepository
import com.khalid.core.feed.model.Feed
import com.khalid.core.utils.Response
import kotlinx.coroutines.flow.Flow

interface FeedRepository : BaseRepository {
    suspend fun getHomeSections(page: Int = 1): Flow<Response<Feed>>
}