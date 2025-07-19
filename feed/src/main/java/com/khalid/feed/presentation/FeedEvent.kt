package com.khalid.feed.presentation

import com.khalid.core_ui.bases.BaseEvent

sealed class FeedEvent : BaseEvent {
    data object LoadFeed : FeedEvent()
    data object RefreshFeed : FeedEvent()
    data object LoadNextPage : FeedEvent()
}