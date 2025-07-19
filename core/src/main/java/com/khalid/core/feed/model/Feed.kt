package com.khalid.core.feed.model

data class Feed(
    val sections: List<FeedSection>,
    val pagination: Pagination
)