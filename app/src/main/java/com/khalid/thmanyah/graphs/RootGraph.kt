package com.khalid.thmanyah.graphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.composable
import com.khalid.core_ui.bases.BaseNavHost
import com.khalid.core_ui.destinations.RootDestination
import com.khalid.feed.presentation.FeedScreen
import com.khalid.feed.presentation.FeedViewModel
import com.khalid.search.presentation.SearchScreen
import com.khalid.search.presentation.SearchViewModel

@Composable
fun RootGraph(
    modifier: Modifier = Modifier,
) {
    BaseNavHost(
        startDestination = RootDestination.FeedScreen,
        modifier = modifier
    ) {
        composable<RootDestination.FeedScreen> {
            val viewModel = hiltViewModel<FeedViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            FeedScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        composable<RootDestination.SearchScreen> {
            val viewModel = hiltViewModel<SearchViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            SearchScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }
}