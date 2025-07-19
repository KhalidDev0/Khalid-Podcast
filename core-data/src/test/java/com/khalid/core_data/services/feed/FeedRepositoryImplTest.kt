package com.khalid.core_data.services.feed

import com.khalid.core.feed.model.Feed
import com.khalid.core.feed.model.Pagination
import com.khalid.core.feed.repository.FeedRepository
import com.khalid.core.utils.Response
import com.khalid.core_data.services.feed.dto.FeedResponseDto
import com.khalid.core_data.services.feed.mappers.FeedMapper
import com.khalid.core_data.utils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class FeedRepositoryImplTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val api: FeedApi = mockk()
    private val mapper: FeedMapper = mockk()
    private val repo: FeedRepository = FeedRepositoryImpl(api, mapper)

    @Test
    fun `getHomeSections emits Success with mapped Feed`() = runTest {
        val page = 1
        val dto = mockk<FeedResponseDto>()
        val mappedFeed = Feed(sections = emptyList(), pagination = Pagination(null, 2))

        coEvery { api.getHomeSections(page) } returns dto
        coEvery { mapper.map(dto) } returns mappedFeed

        val emission = repo.getHomeSections(page).first()

        assertEquals(emission is Response.Success, true)
        emission as Response.Success
        assertEquals(mappedFeed, emission.data)
    }

    @Test
    fun `getHomeSections emits Error when api throws`() = runTest {
        val page = 1
        coEvery { api.getHomeSections(page) } throws RuntimeException("boom!")

        val result = repo.getHomeSections(page).first()

        val expected = Response.Error<Feed>(errorCode = 4000, errorMessage = "Unknown error")
        assertEquals(expected, result)
    }

}
