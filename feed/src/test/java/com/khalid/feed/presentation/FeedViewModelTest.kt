package com.khalid.feed.presentation

import com.khalid.core.feed.model.ContentType
import com.khalid.core.feed.model.Feed
import com.khalid.core.feed.model.FeedSection
import com.khalid.core.feed.model.Pagination
import com.khalid.core.feed.model.Podcast
import com.khalid.core.feed.model.PodcastSection
import com.khalid.core.feed.model.Section
import com.khalid.core.feed.model.SectionLayout
import com.khalid.core.feed.usecase.GetHomeSectionsUseCase
import com.khalid.core.utils.Response
import com.khalid.feed.presentation.utils.MainDispatcherRule
import io.mockk.Called
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FeedViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase: GetHomeSectionsUseCase = mockk(relaxed = true)
    private lateinit var viewModel: FeedViewModel

    private fun section(order: Int): FeedSection {
        val podcast = Podcast(
            podcastId = "id$order",
            name = "podcast$order",
            description = "",
            avatarUrl = "",
            episodeCount = 0,
            duration = 0,
            language = null
        )
        return PodcastSection(
            Section(
                name = "sec$order",
                layout = SectionLayout.SQUARE,
                contentType = ContentType.PODCAST,
                order = order,
                content = listOf(podcast)
            )
        )
    }

    private fun feed(
        vararg orders: Int,
        totalPages: Int = 2
    ) = Feed(
        sections = orders.map { section(it) },
        pagination = Pagination(nextPage = null, totalPages = totalPages)
    )

    private fun stubUseCase(page: Int, feed: Feed) {
        coEvery { useCase.invoke(page) } returns flowOf(Response.Success(feed))
    }

    @Test
    fun `initial load emits sorted sections`() = runTest {
        stubUseCase(1, feed(3, 1, 2))

        viewModel = FeedViewModel(useCase)
        advanceUntilIdle()

        val orders = viewModel.state.value.feed!!.sections.map { it.order }
        assertEquals(listOf(1, 2, 3), orders)
    }

    @Test
    fun `refresh replaces list and keeps global order`() = runTest {
        stubUseCase(1, feed(2, 1))
        stubUseCase(1, feed(4, 3))

        viewModel = FeedViewModel(useCase)
        advanceUntilIdle()

        viewModel.onEvent(FeedEvent.RefreshFeed)
        advanceUntilIdle()

        val orders = viewModel.state.value.feed!!.sections.map { it.order }
        assertEquals(listOf(3, 4), orders)
    }

    @Test
    fun `pagination appends and keeps global order`() = runTest {
        stubUseCase(1, feed(2, 1))
        stubUseCase(2, feed(4, 3))

        viewModel = FeedViewModel(useCase)
        advanceUntilIdle()

        viewModel.onEvent(FeedEvent.LoadNextPage)
        advanceUntilIdle()

        val orders = viewModel.state.value.feed!!.sections.map { it.order }
        assertEquals(listOf(1, 2, 3, 4), orders)
    }

    @Test
    fun `second LoadNextPage ignored while paging is in progress`() = runTest {
        stubUseCase(1, feed(1, totalPages = 3))

        coEvery { useCase.invoke(2) } returns flow {
            emit(Response.Loading())
            delay(Long.MAX_VALUE)
        }

        viewModel = FeedViewModel(useCase)
        advanceUntilIdle()

        viewModel.onEvent(FeedEvent.LoadNextPage)
        advanceUntilIdle()

        coVerify(exactly = 1) { useCase.invoke(2) }
    }

    @Test
    fun `pagination blocked when nextPage exceeds totalPages`() = runTest {
        stubUseCase(1, feed(1, totalPages = 1))

        viewModel = FeedViewModel(useCase)
        advanceUntilIdle()

        clearMocks(useCase)
        viewModel.onEvent(FeedEvent.LoadNextPage)
        advanceUntilIdle()

        coVerify { useCase wasNot Called }
        assertEquals(1, viewModel.state.value.feed!!.sections.size)
    }
}
