package com.khalid.feed.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.khalid.core.feed.model.AudioArticleSection
import com.khalid.core.feed.model.AudioBookSection
import com.khalid.core.feed.model.EpisodeSection
import com.khalid.core.feed.model.PodcastSection
import com.khalid.core.utils.ifNotNullOrEmpty
import com.khalid.core.utils.ifNullOrEmpty
import com.khalid.core_ui.bases.BaseScreen
import com.khalid.core_ui.common.InfiniteLazyColumn
import com.khalid.core_ui.theme.Dimensions
import com.khalid.feed.components.AudioArticleFactory
import com.khalid.feed.components.AudioBookFactory
import com.khalid.feed.components.EpisodeFactory
import com.khalid.feed.components.FeedTopBar
import com.khalid.feed.components.PodcastSectionFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    state: FeedState,
    onEvent: (FeedEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    BaseScreen(
        topBar = { FeedTopBar() },
        modifier = modifier
    ) { innerPadding ->
        PullToRefreshBox(
            state = rememberPullToRefreshState(),
            isRefreshing = state.isRefreshingFeed,
            onRefresh = { onEvent(FeedEvent.RefreshFeed) },
        ) {
            InfiniteLazyColumn(
                isLoadingTail = state.isPaging,
                onLoadMore = { onEvent(FeedEvent.LoadNextPage) },
                contentPadding = PaddingValues(vertical = Dimensions.L),
                loadMoreThreshold = 2,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(innerPadding)
            ) {
                if (state.isLoadingFeed) {
                    item {
                        CircularProgressIndicator(color = Color.White)
                    }
                } else {
                    state.feed?.sections.ifNullOrEmpty {
                        item {
                            Text(
                                "Sorry no media available at the moment",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                            )
                        }
                    }
                }

                state.feed?.sections.ifNotNullOrEmpty {
                    itemsIndexed(it) { index, section ->
                        when (section) {
                            is PodcastSection -> PodcastSectionFactory(section = section)
                            is AudioArticleSection -> AudioArticleFactory(section = section)
                            is AudioBookSection -> AudioBookFactory(section = section)
                            is EpisodeSection -> EpisodeFactory(section = section)
                        }
                    }
                }
            }
        }
    }
}
