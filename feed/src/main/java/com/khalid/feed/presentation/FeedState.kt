package com.khalid.feed.presentation

import com.khalid.core.feed.model.Feed

data class FeedState(
    val isLoadingFeed: Boolean = true,
    val isRefreshingFeed: Boolean = false,
    val isPaging: Boolean = false,
    val nextPage: Int = 1,
    val totalPages: Int = 1,
    val feed: Feed? = null
)
