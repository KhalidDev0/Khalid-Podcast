package com.khalid.feed.presentation

import com.khalid.core.feed.model.Feed
import com.khalid.core.feed.model.FeedSection
import com.khalid.core.feed.usecase.GetHomeSectionsUseCase
import com.khalid.core.utils.update
import com.khalid.core_ui.bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getHomeSectionsUseCase: GetHomeSectionsUseCase,
) : BaseViewModel<FeedState, FeedEvent>() {
    override val privateState = MutableStateFlow(FeedState())

    init {
        onEvent(FeedEvent.LoadFeed)
    }

    override fun onEvent(event: FeedEvent) {
        super.onEvent(event)
        when (event) {
            FeedEvent.LoadFeed -> loadFeed(replace = true)
            FeedEvent.RefreshFeed -> loadFeed(replace = true, isRefresh = true)
            FeedEvent.LoadNextPage -> handlePagination()
        }
    }

    private fun loadFeed(
        page: Int = 1,
        replace: Boolean,
        isRefresh: Boolean = false
    ) = onUseCaseUse(
        useCase = { getHomeSectionsUseCase(page) },
        onLoading = { loading ->
            privateState.update {
                when {
                    isRefresh -> copy(isRefreshingFeed = loading)
                    replace -> copy(isLoadingFeed = loading)
                    else -> copy(isPaging = loading)
                }
            }
        },
        onSuccess = { newFeed ->
            privateState.update {
                val combinedSections: List<FeedSection> =
                    if (replace) newFeed.sections.sortedBy { it.order }
                    else (feed?.sections.orEmpty() + newFeed.sections.sortedBy { it.order })

                copy(
                    feed = Feed(combinedSections, newFeed.pagination),
                    totalPages = newFeed.pagination.totalPages,
                    nextPage = if (replace) 2 else privateState.value.nextPage + 1,
                    isLoadingFeed = false,
                    isPaging = false,
                    isRefreshingFeed = false
                )
            }
        }
    )

    private fun handlePagination() = privateState.value.apply {
        if (isLoadingFeed.not()
            && isRefreshingFeed.not()
            && isPaging.not()
            && nextPage <= totalPages
        ) {
            loadFeed(nextPage, replace = false)
        }
    }

}